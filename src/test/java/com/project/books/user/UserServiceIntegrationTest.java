package com.project.books.user;

import com.project.books.user.address.Address;
import com.project.books.user.address.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class UserServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    User savedUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        addressRepository.deleteAll();

        Address address = new Address();
        address.setRegion("Pomorskie");
        address.setCity("Reda");
        address.setStreet("Gwiazdkowa 23");
        address.setPostCode("50-666");

        User user = new User();
        user.setName("Jania");
        user.setSurname("Kajsa");
        user.setLogin("kajaks");
        user.setPassword("markja");
        user.setEmail("klmak@wp.pl");
        user.setAddress(address);

        savedUser = userRepository.save(user);
    }

    @Test
    void fetchUser_returnDetailsOfUser() throws Exception {

        //given
        Long userId = savedUser.getUserId();

        MockHttpServletRequestBuilder requestBuilder = get("/userid/" + userId)
                .contentType(MediaType.APPLICATION_JSON);

        //when

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);

        assertThat(users.get(0)).satisfies(p -> {
            assertThat(p.getUserId()).isNotNull();
            assertThat(p.getName()).isNotNull();
            assertThat(p.getSurname()).isNotNull();
            assertThat(p.getLogin()).isNotNull();
            assertThat(p.getPassword()).isNotNull();
            assertThat(p.getEmail()).isNotNull();
            assertThat(p.getAddress()).isNotNull();
        });

    }

}
