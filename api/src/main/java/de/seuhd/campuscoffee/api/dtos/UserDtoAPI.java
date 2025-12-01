package de.seuhd.campuscoffee.api.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class UserDtoAPI {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    @Size(min = 1, max = 255, message = "Login name must be between 1 and 255 characters long.")
    @Pattern(regexp = "\\w+", message = "Login name can only contain word characters: [a-zA-Z_0-9]+") //cannot be empty
    private String loginName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters long.")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters long.")
    private String lastName;

    // Getter/Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

