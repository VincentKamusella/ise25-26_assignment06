package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.ports.UserService;
import de.seuhd.campuscoffee.api.dtos.UserDtoAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    @Override public List<User> findAll() { return List.of(); }

    @Override public Optional<User> findById(Long id) { return Optional.empty(); }

    @Override public Optional<User> findByLoginName(String loginName) { return Optional.empty(); }

    @Override public User createUser(UserDtoAPI dto) { return null; }

    @Override public Optional<User> updateUser(Long id, UserDtoAPI dto) { return Optional.empty(); }

    @Override public boolean deleteById(Long id) { return false; }
}
