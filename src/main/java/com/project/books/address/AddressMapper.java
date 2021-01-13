package com.project.books.address;

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
                .users(newAddress.getUser())
                .build();
    }

    public AddressDefinition mapToaddressDefinition(AddressDto addressDto) {
        return AddressDefinition.builder()
                .addressId(addressDto.getAddressId())
                .region(addressDto.getRegion())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .postCode(addressDto.getPostCode())
                .users(addressDto.getUsers())
                .build();
    }
}
