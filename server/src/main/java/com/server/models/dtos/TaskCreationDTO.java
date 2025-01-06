package com.server.models.dtos;

import com.server.models.enums.TaskPriority;
import com.server.models.enums.TaskStatus;
import com.server.models.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationDTO {
    private String title;
    private String description;
    private Long assignedToId;
    private TaskStatus status;
    private TaskPriority priority;
    private Long sprintId;
    private ZonedDateTime deadlineDate;
    private TaskType taskType;
}
