package com.project.books.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    final UserFetchService userFetchService;
    final UserMapper userMapper;
    final UserCreateService userCreateService;

    @GetMapping("/users")
    List<UserDto> getAllUser() {
        return userFetchService.fetchAllUser()
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable Long id) {
        User user = userFetchService.fetchUserById(id);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping("/user/{login}")
    UserDto getUserByLogin(@PathVariable String login) {
        User user = userFetchService.fetchUserByLogin(login);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDefinition userDefinition = userMapper.mapToUserDefinition(userDto);
        User newUser = userCreateService.createUser(userDefinition);
        log.info("create new user: " + newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.mapToUserDto(newUser));
    }
}
