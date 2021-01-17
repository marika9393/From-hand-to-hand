package com.project.books.user.address;

import com.project.books.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    public Long addressId;

    private String region;
    private String city;
    private String street;
    private String postCode;
    private List<UserDto> userDto;
}
