package de.seuhd.campuscoffee.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.seuhd.campuscoffee.api.dtos.UserDtoAPI;
import de.seuhd.campuscoffee.domain.ports.UserService;
import de.seuhd.campuscoffee.domain.impl.UserServiceImpl;
import de.seuhd.campuscoffee.api.mapper.UserDtoMapper;
import org.springframework.http.ResponseEntity;
import de.seuhd.campuscoffee.domain.impl.User;
import org.springframework.web.bind.annotation.*;  
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.net.URI;


@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
private final UserServiceImpl userService; // Domain-Service
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public List<UserDtoAPI> getAllUsers() {
        return userService.findAll().stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoAPI> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/users/filter?loginName=abc
    @GetMapping("/filter")
    public ResponseEntity<UserDtoAPI> getUserByLoginName(@RequestParam("loginName") String loginName) {
        return userService.findByLoginName(loginName)
                .map(UserDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<UserDtoAPI> createUser(@Valid @RequestBody UserDtoAPI request) {
        // Bean Validation greift hier
        User created = userService.createUser(request);
        UserDtoAPI response = UserDtoMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/users/" + response.getId()))
                .body(response);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserDtoAPI> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDtoAPI request
    ) {
        return userService.updateUser(id, request)
                .map(UserDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

