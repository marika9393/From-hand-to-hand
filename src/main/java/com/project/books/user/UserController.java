package com.project.books.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final UserMapper userMapper;


    @GetMapping("/users")
    public List<UserDto> getAllUser() {
        return userService.fetchAllUser();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.fetchUserById(id);
    }

    @GetMapping(value = "/users", params = "login")
    public UserDto getUserByLogin(@RequestParam String login) {
        return userService.fetchUserByLogin(login);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/user/{userId}/roles/{role}")
    public UserDto addRoleToUser(@PathVariable Long userId, @PathVariable String role) {
        final UserDto user = userService.fetchUserById(userId);
        return userService.changeRole(userMapper.mapToUser(user), role);
    }

    @PostMapping("/users/{id}/address/{id}")
    public UserDto addAddressForUser(@PathVariable Long userId, @PathVariable Long addressId) {
        return userService.addAddressForUser(userId, addressId);
    }


    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id) {

        userService.deleteById(id);
    }
}
;

