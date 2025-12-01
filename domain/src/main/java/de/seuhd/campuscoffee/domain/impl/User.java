package de.seuhd.campuscoffee.domain.impl;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String loginName;
    private final String email;
    private final String firstName;
    private final String lastName;

    public User(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String loginName,
            String email,
            String firstName,
            String lastName
    ) {
        this.id = id;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
        this.loginName = Objects.requireNonNull(loginName, "loginName must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User withUpdatedTimestamps(LocalDateTime newUpdatedAt) {
        return new User(
                this.id,
                this.createdAt,
                Objects.requireNonNull(newUpdatedAt, "newUpdatedAt must not be null"),
                this.loginName,
                this.email,
                this.firstName,
                this.lastName
        );
    }

    public User withUpdatedData(String loginName, String email, String firstName, String lastName, LocalDateTime updatedAt) {
        return new User(
                this.id,
                this.createdAt,
                Objects.requireNonNull(updatedAt, "updatedAt must not be null"),
                Objects.requireNonNull(loginName, "loginName must not be null"),
                Objects.requireNonNull(email, "email must not be null"),
                Objects.requireNonNull(firstName, "firstName must not be null"),
                Objects.requireNonNull(lastName, "lastName must not be null")
        );
    }
}
