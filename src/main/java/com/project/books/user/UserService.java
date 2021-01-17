package com.project.books.user;

import com.project.books.exception.NotFoundException;
import com.project.books.user.address.Address;
import com.project.books.user.address.AddressMapper;
import com.project.books.user.address.AddressService;
import com.project.books.user.role.Role;
import com.project.books.user.role.RoleConfiguration;
import com.project.books.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final RoleConfiguration roleConfiguration;
    final UserMapper userMapper;
    final AddressMapper addressMapper;
    final AddressService addressService;


    public List<UserDto> fetchAllUser() {

        List<UserDto> userList = userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
        return userList;
    }

    public UserDto fetchUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user: " + id));
        return userMapper.mapToUserDto(user);
    }

    public UserDto fetchUserByLogin(String login) {
        User byLogin = userRepository.findByLogin(login);
        return userMapper.mapToUserDto(byLogin);
    }

    public UserDto createUser(UserDto userDto) {
//        Role userRole = new Role();
//        userRole.setUserRole(roleConfiguration.getDefaultRole());

        LocalDateTime dateOfRegistration = LocalDateTime.now();
        LocalDateTime dateConverterOfRegistration = dateConverter(dateOfRegistration);


        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .dateOfRegistration(dateConverterOfRegistration)
                .role(userDto.getRole())
                .build();

//        if (!roleRepository.findByUserRole(userRole.getUserRole()).isPresent()) {
//            user.setRole(userRole);
//        }

        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {

        return userRepository.save(user);
    }

    public UserDto changeRole(User user, String role) {
        Role userRole = new Role();
        roleConfiguration.getRoles().stream().filter(x -> x.equals(role))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Role not exist: " + role));
        userRole.setUserRole(role);
        user.setRole(userRole);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public UserDto addAddressForUser(Long userId, Long addressId) {
        User user = userMapper.mapToUser(fetchUserById(userId));
        Address address = addressMapper.mapToAddress(addressService.fetchAddressById(addressId));
        user.setAddress(address);

        return userMapper.mapToUserDto(userRepository.save(user));
    }

    private LocalDateTime dateConverter(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String format = date.format(dateTimeFormatter);
        return LocalDateTime.parse(format, dateTimeFormatter);
    }
}

