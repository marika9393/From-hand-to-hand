package com.project.books.user;

import com.project.books.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateService {

    final UserRepository userRepository;
    final UserMapper userMapper;

    public User createUser(UserDefinition userDefinition) {
        String name = userDefinition.getName();
        String surname = userDefinition.getSurname();
        String login = userDefinition.getLogin();
        String password = userDefinition.getPassword();
        String email = userDefinition.getEmail();


        User user = User.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .build();
        return userRepository.save(user);
    }


}
