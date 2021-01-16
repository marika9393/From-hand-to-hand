package com.project.books.user;

import com.project.books.exception.NotFoundException;
import com.project.books.user.address.Address;
import com.project.books.user.role.Role;
import com.project.books.user.role.RoleConfiguration;
import com.project.books.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final RoleConfiguration roleConfiguration;
    final UserMapper userMapper;


    public List<User> fetchAllUser() {

        return userRepository.findAll();
    }

    public User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user: " + id));
    }

    public User fetchUserByLogin(String login) {

        return userRepository.findByLogin(login);
    }

    public UserDto createUser(UserDto userDto) {
//        Role userRole = new Role();
//        userRole.setUserRole(roleConfiguration.getDefaultRole());

        String name = userDto.getName();
        String surname = userDto.getSurname();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        Address address = userDto.getAddress();

        LocalDateTime dateOfRegistration = LocalDateTime.now();
        LocalDateTime dateConverterOfRegistration = dateConverter(dateOfRegistration);


        User user = User.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .dateOfRegistration(dateConverterOfRegistration)
                .address(address)
                .build();

//        if (!roleRepository.findByUserRole(userRole.getUserRole()).isPresent()) {
//            user.setRole(userRole);
//        }

        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private LocalDateTime dateConverter(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String format = date.format(dateTimeFormatter);
        return LocalDateTime.parse(format, dateTimeFormatter);
    }

    private boolean mailChecker(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        return matchFound;
    }

    private boolean loginExistChecker(String login) {

        if (userRepository.findByLogin(login) != null) {
            System.out.println("Login zajęty");
        }
        return true;
    }

}

