package com.server.models.Task;

import com.server.models.dtos.TaskCreationDTO;

import java.time.ZonedDateTime;

public class TaskFactory {
    public static AbstractTask createTask (TaskCreationDTO task) {
        switch (task.getTaskType()) {
            case DEVELOPMENT -> {
                return buildDevelopmentTask(task);
            }
            case BUG_FIX -> {
                return buildBugFixTask(task);
            }
            default -> throw new IllegalArgumentException ("Passed task is of unknown type.");
        }
    }

    private static DevelopmentTask buildDevelopmentTask(TaskCreationDTO task) {
        return DevelopmentTask.builder()
                .from(task)
                .sprintId(task.getSprintId())
                .build();
    }

    private static BugFixTask buildBugFixTask(TaskCreationDTO task) {
        ZonedDateTime deadlineDate = task.getDeadlineDate();
        if (deadlineDate.toLocalDateTime().equals(deadlineDate.toLocalDate().atStartOfDay())) {
            switch (task.getPriority()) {
                case LOW -> deadlineDate = deadlineDate.plusDays(6);
                case MEDIUM -> deadlineDate = deadlineDate.plusDays(4);
                case HIGH -> deadlineDate = deadlineDate.plusDays(2);
                default -> throw new IllegalArgumentException("Passed task contains an unknown type of priority.");
            }
        }
        return BugFixTask.builder()
                .from(task)
                .deadlineDate(deadlineDate)
                .build();
    }
}
