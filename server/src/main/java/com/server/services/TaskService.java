package com.server.services;

import com.server.models.Task;
import com.server.models.dtos.TaskNavigationDto;
import com.server.models.enums.TaskStatus;
import com.server.repositories.TaskRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task createTask(TaskNavigationDto taskNavigationDto) {
    Task task = new Task();
    task.setTitle(taskNavigationDto.getTitle());
    task.setDescription(taskNavigationDto.getDescription());
    task.setStatus(TaskStatus.TODO);
    task.setPriority(taskNavigationDto.getPriority());
    task.setAssignedTo(taskNavigationDto.getAssignedTo());
    return taskRepository.save(task);
  }

  public Task updateTask(Long id, TaskNavigationDto taskNavigationDto) {
    Task task =
        taskRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    task.setTitle(taskNavigationDto.getTitle());
    task.setDescription(taskNavigationDto.getDescription());
    task.setPriority(taskNavigationDto.getPriority());
    task.setStatus(taskNavigationDto.getStatus());
    task.setAssignedTo(taskNavigationDto.getAssignedTo());
    return taskRepository.save(task);
  }
}
