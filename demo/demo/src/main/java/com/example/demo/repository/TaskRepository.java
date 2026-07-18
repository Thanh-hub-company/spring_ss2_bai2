package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    // Khởi tạo dữ liệu giả lập ban đầu (10 tasks)
    public TaskRepository() {
        tasks.add(new Task(1L, "Task 1", "Setup project structure", "HIGH", 1L));
        tasks.add(new Task(2L, "Task 2", "Create User Model", "MEDIUM", 2L));
        tasks.add(new Task(3L, "Task 3", "Create Task Model", "MEDIUM", 3L));
        tasks.add(new Task(4L, "Task 4", "Implement Repositories", "HIGH", 1L));
        tasks.add(new Task(5L, "Task 5", "Implement Services", "LOW", 2L));
        tasks.add(new Task(6L, "Task 6", "Implement Controllers", "HIGH", 3L));
        tasks.add(new Task(7L, "Task 7", "Test API endpoints", "MEDIUM", 1L));
        tasks.add(new Task(8L, "Task 8", "Fix injection bugs", "HIGH", 2L));
        tasks.add(new Task(9L, "Task 9", "Write documentation", "LOW", 3L));
        tasks.add(new Task(10L, "Task 10", "Deploy to staging", "MEDIUM", 1L));
    }

    // Phương thức lấy toàn bộ danh sách Task
    public List<Task> findAll() {
        return tasks;
    }

    // Thêm hàm này vào class TaskRepository để lưu task mới
    public Task save(Task task) {
        // Tự động tăng ID giả lập dựa trên size của list hiện tại
        long nextId = tasks.size() + 1;
        task.setId(nextId);
        tasks.add(task);
        return task;
    }
}