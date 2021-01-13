package com.project.books.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

//    Address fetchAddressById(Long id){
//        return addressRepository.findById(id)
//                .orElseThrow()
//    }
}
