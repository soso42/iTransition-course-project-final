package com.example.courseproject.service.impl;

import com.example.courseproject.dto.UserCreationDTO;
import com.example.courseproject.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;


    @Test
    void findByUsername() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {

    }

    @Test
    void deleteAll() {
    }

    @Test
    void registerNewUser() {
        //Given
        UserCreationDTO dto = new UserCreationDTO();
        dto.setUsername("Soso");
        dto.setPassword("1");
        dto.setEmail("sosojanashvili@gmail.com");

        //when
        User registeredUser = userService.registerNewUser(dto);

        //then
        assertNotNull(registeredUser);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void userAlreadyRegistered() {
    }

    @Test
    void makeAdmins() {
    }

    @Test
    void removeAdmins() {
    }

    @Test
    void blockAll() {
    }

    @Test
    void unblockAll() {
    }
}
