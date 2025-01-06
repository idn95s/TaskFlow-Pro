package com.server.models.Task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.models.User.User;
import com.server.models.dtos.TaskCreationDTO;
import com.server.models.enums.TaskPriority;
import com.server.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@Entity(name = "Task")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTask implements Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private User assignedTo;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static abstract class AbstractTaskBuilder<C extends AbstractTask, B extends AbstractTaskBuilder<C, B>> {

        public B from(TaskCreationDTO taskCreationDTO) {
            this.title = taskCreationDTO.getTitle();
            this.description = taskCreationDTO.getDescription();
            this.status = taskCreationDTO.getStatus();
            this.priority = taskCreationDTO.getPriority();
            return self();
        }
    }

    @PrePersist
    public void onCreate() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = ZonedDateTime.now();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass =
                o instanceof HibernateProxy
                        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                        : o.getClass();
        Class<?> thisEffectiveClass =
                this instanceof HibernateProxy
                        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                        : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
