package com.server.Task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.ServerApplication;
import com.server.models.dtos.TaskCreationDTO;
import com.server.models.enums.TaskPriority;
import com.server.models.enums.TaskStatus;
import com.server.models.enums.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createTask_ShouldReturnCreated() throws Exception {
    TaskCreationDTO taskDto =
        new TaskCreationDTO(
            "New Task: " + LocalTime.now(),
            "Task Description",
            1L,
            TaskStatus.TODO,
            TaskPriority.HIGH,
            10L,
            null,
            TaskType.DEVELOPMENT);

    mockMvc
        .perform(
            post("/api/tasks/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isCreated());
  }

  @Test
  void updateTask_ShouldReturnOK() throws Exception {
    long taskId = 1L;

    TaskCreationDTO taskDto =
        new TaskCreationDTO(
            "Updated Task: " + LocalTime.now(),
            "Updated Task Description",
            2L,
            TaskStatus.IN_PROGRESS,
            TaskPriority.MEDIUM,
            null,
            ZonedDateTime.now(),
            TaskType.BUG_FIX);

    mockMvc
        .perform(
            put("/api/tasks/" + taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isOk());
  }

  @Test
  void updateTask_ShouldReturnNotFound() throws Exception {
    long nonExistingTaskId = -1L;

    TaskCreationDTO taskDto =
        new TaskCreationDTO(
            "Updated Task",
            "Updated Task Description",
            2L,
            TaskStatus.IN_PROGRESS,
            TaskPriority.MEDIUM,
            null,
            ZonedDateTime.now(),
            TaskType.BUG_FIX);

    mockMvc
        .perform(
            put("/api/tasks/" + nonExistingTaskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isNotFound());
  }
}
