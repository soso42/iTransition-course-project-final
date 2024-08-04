package com.example.courseproject.service;

import com.example.courseproject.entity.User;
import com.example.courseproject.dto.UserCreationDTO;
import com.example.courseproject.dto.UserListDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    User save(User user);
    void deleteAll(List<User> users);
    void blockAll(List<User> users);
    void unblockAll(List<User> users);
    User registerNewUser(UserCreationDTO dto);
    UserListDTO getAllUsers();
    Boolean userAlreadyRegistered(String username);
    void makeAdmins(List<User> users);
    void removeAdmins(List<User> users);
}
