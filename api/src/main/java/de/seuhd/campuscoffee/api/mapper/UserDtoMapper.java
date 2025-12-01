package de.seuhd.campuscoffee.api.mapper;

import de.seuhd.campuscoffee.api.dtos.UserDtoAPI;
import de.seuhd.campuscoffee.domain.impl.User;

public interface UserDtoMapper {
    public final class UserMapper {

    private UserMapper() {}

    public static UserDtoAPI toDto(User user) {
        UserDtoAPI dto = new UserDtoAPI();
        dto.setId(user.getId());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setLoginName(user.getLoginName());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public static User toDomain(UserDtoAPI dto) {
        // createdAt / updatedAt können vom Service gesetzt werden, wenn null
        return new User(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getLoginName(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName()
        );
    }
    }
}