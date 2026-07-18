package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1L, "nguyenvana", "ana@gmail.com", "ADMIN"));
        users.add(new User(2L, "lethib", "bthi@gmail.com", "USER"));
        users.add(new User(3L, "tranvanc", "ctran@gmail.com", "USER"));
    }

    public List<User> findAll() {
        return users;
    }
}
