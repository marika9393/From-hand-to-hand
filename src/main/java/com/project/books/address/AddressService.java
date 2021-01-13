package com.project.books.address;

import com.project.books.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public Address fetchAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Not found address: " + id));
    }

    public List<Address> fetchAllAddress() {

        return addressRepository.findAll();
    }

    public Address createAddress(AddressDefinition addressDefinition) {

        String region = addressDefinition.getRegion();
        String city = addressDefinition.getCity();
        String street = addressDefinition.getStreet();
        String postCode = addressDefinition.getPostCode();


        if (region.isEmpty() || region.isBlank()) {
            throw new BadRequestException("Pole z województwem nie może być puste");
        }
        if (city.isEmpty() || city.isBlank()) {
            throw new BadRequestException("Pole z miastem nie może byc puste");
        }
        if (street.isEmpty() || city.isBlank()) {
            throw new BadRequestException("Pole z nazwą ulicy nie może być puste");
        }
        if (postCode.isEmpty() || postCode.isBlank()) {
            throw new BadRequestException("pole z kodem pocztowym nie może być puste");
        }

        Address address = Address.builder()
                .region(addressDefinition.getRegion())
                .city(addressDefinition.getCity())
                .street(addressDefinition.getStreet())
                .postCode(addressDefinition.getPostCode())
                .user(addressDefinition.getUsers())
                .build();

        return addressRepository.save(address);

    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }


}
