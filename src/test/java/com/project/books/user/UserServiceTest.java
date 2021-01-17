package com.project.books.user;

import com.project.books.user.address.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserService userService;

    @Test
    void userServiceTest_createUser() {
        //given

        Address address = new Address();
        address.setRegion("Pomorskie");
        address.setCity("Reda");
        address.setStreet("Gwiazdkowa 23");
        address.setPostCode("50-666");

        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(new UserDto());

        //when
        UserDto result = userService.createUser(UserDto.builder()
                .userId(null)
                .name("Jania")
                .surname("Kajsa")
                .login("kajaks")
                .password("markja")
                .email("klmak@wp.pl")
                .build());

        //then
        assertThat(result).isInstanceOf(UserDto.class);
        verify(userRepository).save(any(User.class));


    }
}
