package com.server.models.Task;

import com.server.models.enums.TaskType;
import jakarta.persistence.Entity;

public interface Task {
    Long getId ();
    TaskType getTaskType ();
}
