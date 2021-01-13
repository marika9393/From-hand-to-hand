package com.project.books.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
