package com.server.services;

import com.server.models.Task.AbstractTask;
import com.server.models.Task.Task;
import com.server.models.Task.TaskFactory;
import com.server.models.User.User;
import com.server.models.dtos.TaskCreationDTO;
import com.server.repositories.TaskRepository;
import java.util.List;

import com.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;

  private final UserRepository userRepository;

  public List<AbstractTask> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public Task createTask(TaskCreationDTO taskCreationDTO) {
    AbstractTask task = TaskFactory.createTask(taskCreationDTO);
    User user = userRepository.findById(taskCreationDTO.getAssignedToId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    task.setAssignedTo(user);
    return taskRepository.save(task);
  }

  public Task updateTask(Long id, TaskCreationDTO taskCreationDTO) {
    AbstractTask task =
        taskRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return taskRepository.save(task);
  }
}
