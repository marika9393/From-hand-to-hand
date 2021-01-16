package com.project.books.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
//@RequestMapping("/users")
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

    //http://localhost:8080/daseqw
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.fetchUserById(id);
        return userMapper.mapToUserDto(user);
    }

    // /users?login=dshihuwerre
    @GetMapping(value = "/users", params = "login")
    public UserDto getUserByLogin(@RequestParam String login) {
        User user = userService.fetchUserByLogin(login);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/user/{userId}/roles/{role}")
    public UserDto addRoleToUser(@PathVariable Long userId, @PathVariable String role){
        final User user = userService.fetchUserById(userId);
        return userService.changeRole(user, role);
    }


    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id) {

        userService.deleteById(id);
    }

    @PostMapping("/users/{id}/address/{id}")
    public void addAddressForUser(@PathVariable Long userId, @PathVariable Long adresId) {
        //weź użytkownika o danym id
        //weź adres o danym id
        //na user.setAddress(adres poprany z pooprzedniej linijki)
        // zapis to bazy (userRepository.save(user)
    }
}
