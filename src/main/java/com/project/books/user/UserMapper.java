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
                .address(newUser.getAddress())
                .books(newUser.getBooks())
                .booking(newUser.getBookings())
                .build();
    }

}
