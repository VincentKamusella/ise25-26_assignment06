package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.api.dtos.UserDtoAPI;
import de.seuhd.campuscoffee.domain.impl.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByLoginName(String loginName);
    User createUser(UserDtoAPI dto);
    Optional<User> updateUser(Long id, UserDtoAPI dto);
    boolean deleteById(Long id);
}
