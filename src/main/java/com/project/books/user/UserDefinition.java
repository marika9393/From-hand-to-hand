package com.project.books.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDefinition {


    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
//    private LocalDate dateOfRegistration;
}
