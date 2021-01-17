package com.project.books.user.address;

import org.springframework.stereotype.Component;

@Component
public class AddressMapper {


    public AddressDto mapToAddressDto(Address newAddress) {
        return AddressDto.builder()
                .addressId(newAddress.getAddressId())
                .region(newAddress.getRegion())
                .city(newAddress.getCity())
                .street(newAddress.getStreet())
                .postCode(newAddress.getPostCode())
                .build();
    }

    public Address mapToAddress(AddressDto newAddress) {
        return Address.builder()
                .addressId(newAddress.getAddressId())
                .region(newAddress.getRegion())
                .city(newAddress.getCity())
                .street(newAddress.getStreet())
                .postCode(newAddress.getPostCode())
                .build();
    }
}
