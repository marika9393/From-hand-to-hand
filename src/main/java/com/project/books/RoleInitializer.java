package com.project.books;

import com.project.books.user.role.Role;
import com.project.books.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    public static final String DEFAULT_ROLE = "USER";

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(null, DEFAULT_ROLE));
    }
}
