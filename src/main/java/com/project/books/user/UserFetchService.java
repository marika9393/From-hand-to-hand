package com.project.books.user;

import com.project.books.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFetchService {

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
}
