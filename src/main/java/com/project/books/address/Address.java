package com.project.books.address;


import com.project.books.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long addressId;

    private String region;
    private String city;
    private String street;
    private String postCode;

    @OneToMany(mappedBy = "userAddress")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> user;

}
