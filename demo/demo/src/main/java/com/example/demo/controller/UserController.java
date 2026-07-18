package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "search", required = false) String search) {
        // Lấy toàn bộ danh sách từ tầng Service
        List<User> allUsers = userService.findAllUsers();

        // Nếu có tham số search, tiến hành lọc gần đúng theo username
        if (search != null && !search.trim().isEmpty()) {
            allUsers = allUsers.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(search.toLowerCase().trim()))
                    .collect(Collectors.toList());
        }

        // Trả về kết quả bọc trong ResponseEntity với trạng thái 200 OK
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}