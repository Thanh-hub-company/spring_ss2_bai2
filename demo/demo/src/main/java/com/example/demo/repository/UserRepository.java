package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    // Khởi tạo dữ liệu giả lập ban đầu (3 users)
    public UserRepository() {
        users.add(new User(1L, "nguyenvana", "ana@gmail.com", "ADMIN"));
        users.add(new User(2L, "lethib", "bthi@gmail.com", "USER"));
        users.add(new User(3L, "tranvanc", "ctran@gmail.com", "USER"));
    }

    // Phương thức lấy toàn bộ danh sách User
    public List<User> findAll() {
        return users;
    }

    // --- PHƯƠNG THỨC MỚI: Tìm kiếm một User theo ID ---
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id)) // Lọc ra User có ID khớp với tham số truyền vào
                .findFirst()                             // Lấy ra phần tử đầu tiên tìm thấy
                .orElse(null);                           // Nếu không tìm thấy ai, trả về null
    }
}
