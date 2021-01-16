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

    final UserService userService;
    final UserMapper userMapper;


    @GetMapping("/users")
    public List<UserDto> getAllUser() {
        return userService.fetchAllUser()
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/userid/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.fetchUserById(id);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping("/user/{login}")
    public UserDto getUserByLogin(@PathVariable String login) {
        User user = userService.fetchUserByLogin(login);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDefinition userDefinition = userMapper.mapToUserDefinition(userDto);
        User newUser = userService.createUser(userDefinition);
        log.info("create new user: " + newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.mapToUserDto(newUser));
    }


    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
