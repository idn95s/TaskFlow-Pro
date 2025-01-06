package com.server.models.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    private String passwordHash;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = ZonedDateTime.now();
    }
}
