package com.server.Task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.ServerApplication;
import com.server.models.dtos.TaskNavigationDto;
import com.server.models.enums.TaskPriority;
import com.server.models.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = ServerApplication.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createTask_ShouldReturnCreated() throws Exception {
    TaskNavigationDto taskDto =
        new TaskNavigationDto(
            "New Task", "Task Description", "John Paul", TaskStatus.TODO, TaskPriority.HIGH);

    mockMvc
        .perform(
            post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isCreated());
  }

  @Test
  void updateTask_ShouldReturnOK() throws Exception {
    Long taskId = 1L;

    TaskNavigationDto taskDto =
        new TaskNavigationDto(
            "Updated Task",
            "Updated Task Description",
            "Jane Doe",
            TaskStatus.IN_PROGRESS,
            TaskPriority.MEDIUM);

    mockMvc
        .perform(
            put("/api/tasks/" + taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isOk());
  }

  @Test
  void updateTask_ShouldReturnNotFound() throws Exception {
    Long nonExistingTaskId = -1L;

    TaskNavigationDto taskDto =
        new TaskNavigationDto(
            "Updated Task",
            "Updated Task Description",
            "Jane Doe",
            TaskStatus.IN_PROGRESS,
            TaskPriority.MEDIUM);

    mockMvc
        .perform(
            put("/api/tasks/" + nonExistingTaskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isNotFound());
  }
}
