package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Hàm GET lấy danh sách
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "search", required = false) String search) {
        List<Task> allTasks = taskService.findAllTasks();
        if (search != null && !search.trim().isEmpty()) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getTitle().toLowerCase().contains(search.toLowerCase().trim()))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(allTasks);
    }

    // 2. Hàm POST tạo mới (Bắt buộc phải có @PostMapping)
    @PostMapping
    public ResponseEntity<?> createNewTask(@RequestBody Task newTask) {
        Task savedTask = taskService.createTask(newTask);

        if (savedTask == null) {
            return new ResponseEntity<>("Lỗi: Người được giao việc (assignedTo) không tồn tại trong hệ thống!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
}