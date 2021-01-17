package com.project.books.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto mapToUserDto(User newUser) {
        return UserDto.builder()
                .userId(newUser.getUserId())
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .dateOfRegistration(newUser.getDateOfRegistration())
                .role(newUser.getRole())
                .build();
    }

    User mapToUser(UserDto newUser) {
        return User.builder()
                .userId(newUser.getUserId())
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .dateOfRegistration(newUser.getDateOfRegistration())
                .role(newUser.getRole())
                .build();
    }

}
