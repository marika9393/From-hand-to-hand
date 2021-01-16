package com.project.books.user;

import com.project.books.booking.Booking;
import com.project.books.books.Books;
import com.project.books.user.address.Address;
import com.project.books.user.role.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;


    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private LocalDateTime dateOfRegistration;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Address address;

    @ManyToOne
    private Role role;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Books> books;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Booking> bookings;

}
