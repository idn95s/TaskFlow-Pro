package com.server.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.models.enums.TaskPriority;
import com.server.models.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskNavigationDto {
  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("assignedTo")
  private String assignedTo;

  @JsonProperty("status")
  private TaskStatus status;

  @JsonProperty("priority")
  private TaskPriority priority;
}
