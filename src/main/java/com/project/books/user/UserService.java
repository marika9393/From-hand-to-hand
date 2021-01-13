package com.project.books.user;

import com.project.books.exception.BadRequestException;
import com.project.books.exception.NotFoundException;
import com.project.books.exception.UserAlredyExists;
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


    List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user: " + id));
    }

    User fetchUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User createUser(UserDefinition userDefinition) {

        String name = userDefinition.getName();
        String surname = userDefinition.getSurname();
        String login = userDefinition.getLogin();
        String password = userDefinition.getPassword();
        String email = userDefinition.getEmail();
        LocalDateTime dateOfRegistration = LocalDateTime.now();
        LocalDateTime dataConverterOfRegistration = dateConverter(dateOfRegistration);


        if(name.isBlank() || name.isEmpty()){
            throw new BadRequestException("Pole z imieniem nie może pozostać puste");

        }
        if (surname.isBlank() || surname.isEmpty()){
            throw new BadRequestException("Pole z nazwiskiem nie może pozostać puste");
        }
        if (login.isEmpty() || login.isBlank()){
            throw new BadRequestException("Pole z loginem nie może pozostać puste");
        }
        if(password.length() < 5) {
            throw new BadRequestException("Hasło musi zawierać conajmniej 5 dowolnych znaków");
        }
        if (email.isBlank() || email.isEmpty()){
            throw new BadRequestException("Pole z adresem e-mail nie może pozostać puste");
        }
        if (!mailChecker(email)){
            throw  new BadRequestException("Niepoprawny adres e-mail");
        }
        if (loginExistChecker(login)){
            throw new UserAlredyExists("Użytkownik z podanym loginem już istnieje");
        }


        User user = User.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .dateOfRegistration(dataConverterOfRegistration)
                .build();
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private LocalDateTime dateConverter(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String format = date.format(dateTimeFormatter);
        return LocalDateTime.parse(format, dateTimeFormatter);
    }

    private boolean mailChecker(String email){
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        return matchFound;
    }

    private boolean loginExistChecker(String login) {

        if (userRepository.findByLogin(login) != null){
            System.out.println("Login zajęty");
        }
        return true;
    }
}

