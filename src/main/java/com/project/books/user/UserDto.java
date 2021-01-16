package com.project.books.user;

import com.project.books.booking.Booking;
import com.project.books.books.Books;
import com.project.books.user.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public Long userId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private LocalDateTime dateOfRegistration;
    private Address address;
    private List<Books> books;
    private List<Booking> booking;
}
