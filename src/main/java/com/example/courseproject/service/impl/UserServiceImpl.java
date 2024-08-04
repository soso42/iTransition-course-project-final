package com.example.courseproject.service.impl;

import com.example.courseproject.entity.User;
import com.example.courseproject.enums.Role;
import com.example.courseproject.dto.UserCreationDTO;
import com.example.courseproject.dto.UserListDTO;
import com.example.courseproject.repository.UserRepository;
import com.example.courseproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteAll(List<User> users) {
        userRepository.deleteAll(users);
    }

    @Override
    public User registerNewUser(UserCreationDTO dto) {
        User user = new User();
        modelMapper.map(dto, user);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER.toString());
        return save(user);
    }

    @Override
    public UserListDTO getAllUsers() {
        List<User> userList = userRepository.findAll();
        UserListDTO dto = new UserListDTO();
        dto.setUsers(userList);
        return dto;
    }

    @Override
    public Boolean userAlreadyRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void makeAdmins(List<User> users) {
        setRoleToUsers(users, Role.ADMIN);
    }

    @Override
    public void removeAdmins(List<User> users) {
        setRoleToUsers(users, Role.USER);
    }

    private void setRoleToUsers(List<User> users, Role role) {
        users.forEach(user -> {
            User dbUser = userRepository.findById(user.getUserId()).get();
            dbUser.setRole(role.toString());
            userRepository.save(dbUser);
        });
    }

    @Override
    public void blockAll(List<User> users) {
        enableOrDisableUsers(users, false);
    }

    @Override
    public void unblockAll(List<User> users) {
        enableOrDisableUsers(users, true);
    }

    private void enableOrDisableUsers(List<User> users, Boolean enable) {
        users.forEach(user -> {
            User dbUser = userRepository.findById(user.getUserId()).get();
            dbUser.setEnabled(enable);
            userRepository.save(dbUser);
        });
    }



}
