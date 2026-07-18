package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService; // Tiêm thêm UserService vào đây

    // Constructor Injection: Tiêm cả TaskRepository và UserService vào cùng một lúc
    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    // Phương thức lấy toàn bộ danh sách Task
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    // Hàm xử lý logic tạo mới Task và Validate User trước khi lưu
    public Task createTask(Task newTask) {
        // 1. Kiểm tra assignedTo xem user đó có tồn tại trong hệ thống hay không
        User assignedUser = userService.findUserById(newTask.getAssignedTo());

        // 2. Nếu không tìm thấy User (trả về null), lập tức trả về null để báo cho Controller biết
        if (assignedUser == null) {
            return null;
        }

        // 3. Nếu tìm thấy User hợp lệ, tiến hành gọi Repository để lưu Task vào danh sách
        return taskRepository.save(newTask);
    }
}