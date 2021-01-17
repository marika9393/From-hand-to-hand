package com.project.books.user.address;

import com.project.books.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressService {


    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressDto fetchAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Not found address: " + id));
        return addressMapper.mapToAddressDto(address);
    }

    public List<AddressDto> fetchAllAddress() {


        List<AddressDto> addressDtoList = addressRepository.findAll()
                .stream()
                .map(addressMapper::mapToAddressDto)
                .collect(Collectors.toList());
        return addressDtoList;
    }

    public AddressDto createAddress(AddressDto addressDto) {

        Address address = Address.builder()
                .region(addressDto.getRegion())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .postCode(addressDto.getPostCode())
                .build();

        return addressMapper.mapToAddressDto(addressRepository.save(address));

    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

}
