package org.scaler.userservice.dtos;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.scaler.userservice.models.Role;
import org.scaler.userservice.models.User;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getUsername();
        userDto.roles = user.getRoles();
        userDto.isEmailVerified = user.getIsEmailVerified();

        return userDto;
    }
}