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

    UserDefinition mapToUserDefinition(UserDto userDto) {
        return UserDefinition.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .dateOfRegistration(userDto.getDateOfRegistration())
                .address(userDto.getAddress())
                .books(userDto.getBooks())
                .booking(userDto.getBooking())
                .build();
    }
}
