package com.project.books.user;

import com.project.books.user.address.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final AddressMapper addressMapper;

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
                //.address(addressMapper.mapToAddressDto(newUser.getAddress()))
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
                //.address(addressMapper.mapToAddress(newUser.getAddress()))
                .build();
    }

}
