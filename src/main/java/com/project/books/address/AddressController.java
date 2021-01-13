package com.project.books.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AddressController {


    final AddressService addressService;
    final AddressMapper addressMapper;

    @GetMapping("/address")
    List<AddressDto> getAllAddress() {
        return addressService.fetchAllAddress()
                .stream()
                .map(addressMapper::mapToAddressDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/address/{id}")
    AddressDto getAddressById(@PathVariable Long id) {
        Address address = addressService.fetchAddressById(id);
        return addressMapper.mapToAddressDto(address);
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDefinition addressDefinition = addressMapper.mapToaddressDefinition(addressDto);
        Address address = addressService.createAddress(addressDefinition);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressMapper.mapToAddressDto(address));


    }

    @DeleteMapping("/address/{id}")
    public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
    }


}
