package com.project.books.user.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AddressController {
    final AddressService addressService;
    final AddressMapper addressMapper;

    @GetMapping("/address")
    List<AddressDto> getAllAddress() {
        return addressService.fetchAllAddress();
    }

    @GetMapping("/address/{id}")
    AddressDto getAddressById(@PathVariable Long id) {
        return addressService.fetchAddressById(id);
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto createAddress(@RequestBody AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @DeleteMapping("/address/{id}")
    public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
    }


}
