package com.project.books.user.address;


import com.project.books.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long addressId;

    private String region;
    private String city;
    private String street;
    private String postCode;

}
