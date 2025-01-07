package com.server.controllers;

import com.server.models.Task.AbstractTask;
import com.server.models.Task.Task;
import com.server.models.dtos.TaskCreationDTO;
import com.server.services.TaskService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  @GetMapping
  public List<AbstractTask> getAllTasks() {
    return taskService.getAllTasks();
  }

  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable Long id) {
    return taskService.getTaskById(id);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody TaskCreationDTO taskCreationDTO) {
    return taskService.createTask(taskCreationDTO);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, TaskCreationDTO taskCreationDTO) {
    return taskService.updateTask(id, taskCreationDTO);
  }
}
