package com.server.models.Task;

import com.server.models.enums.TaskType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class DevelopmentTask extends AbstractTask {
    private Long sprintId;

    @Override
    public TaskType getTaskType() {
        return TaskType.DEVELOPMENT;
    }
}
