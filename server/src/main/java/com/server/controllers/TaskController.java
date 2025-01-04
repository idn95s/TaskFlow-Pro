package com.server.controllers;

import com.server.models.Task;
import com.server.models.dtos.TaskNavigationDto;
import com.server.services.TaskService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody TaskNavigationDto taskNavigationDto) {
    return taskService.createTask(taskNavigationDto);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, TaskNavigationDto taskNavigationDto) {
    return taskService.updateTask(id, taskNavigationDto);
  }
}
