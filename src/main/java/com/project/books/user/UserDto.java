package com.project.books.user;

import com.project.books.booking.Booking;
import com.project.books.books.Books;
import com.project.books.user.address.Address;
import com.project.books.user.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public Long userId;
    @NotBlank
    private String name;
    private String surname;
    private String login;
    private String password;

    @Email(message = "email has incorrect format")
    @NotNull
    private String email;
    private LocalDateTime dateOfRegistration;
    private Address address;
    //private AddressDto address;
    //private List<BookDto> books;
//    private Role role;
//    private List<Books> books;
//    private List<Booking> booking;
}
