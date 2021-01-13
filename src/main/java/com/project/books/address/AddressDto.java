package com.project.books.address;

import com.project.books.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    Long addressId;

    private String region;
    private String city;
    private String street;
    private String postCode;
    private List<User> users;
}
