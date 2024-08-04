package com.example.courseproject.controller;

import com.example.courseproject.dto.UserCreationDTO;
import com.example.courseproject.dto.UserListDTO;
import com.example.courseproject.entity.User;
import com.example.courseproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserCreationDTO dto) {
        User user = new User();
        if (userService.userAlreadyRegistered(dto.getUsername())) {
            return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
        }
        user = userService.registerNewUser(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/all-users")
    public ResponseEntity<UserListDTO> getAllUsers() {
        UserListDTO userListDTO = userService.getAllUsers();
        return new ResponseEntity<>(userListDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/delete-users")
    public ResponseEntity<UserListDTO> deleteUsers(@RequestBody UserListDTO dto) {
        userService.deleteAll(dto.getUsers());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/admin/block-users")
    public ResponseEntity<UserListDTO> blockUsers(@RequestBody UserListDTO dto) {
        userService.blockAll(dto.getUsers());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/admin/unblock-users")
    public ResponseEntity<UserListDTO> unBlockUsers(@RequestBody UserListDTO dto) {
        userService.unblockAll(dto.getUsers());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/admin/make-admins")
    public ResponseEntity<UserListDTO> makeUsersAdmins(@RequestBody UserListDTO dto) {
        userService.makeAdmins(dto.getUsers());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/admin/remove-admins")
    public ResponseEntity<UserListDTO> removeAdmins(@RequestBody UserListDTO dto) {
        userService.removeAdmins(dto.getUsers());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
