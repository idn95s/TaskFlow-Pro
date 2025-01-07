package com.server.models.Task;

import com.server.models.enums.TaskType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class BugFixTask extends AbstractTask {
    private ZonedDateTime deadlineDate;

    @Override
    public TaskType getTaskType() {
        return TaskType.BUG_FIX;
    }
}
