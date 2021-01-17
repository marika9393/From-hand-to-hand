package com.project.books.user;

import com.project.books.user.address.AddressDto;
import com.project.books.user.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public Long userId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String login;
    @Length(min = 5)
    private String password;
    @Email
    private String email;
    private LocalDateTime dateOfRegistration;
    private AddressDto address;
    private Role role;
//    private List<BooksDto> books;
//    private List<BookingDto> booking;
}
