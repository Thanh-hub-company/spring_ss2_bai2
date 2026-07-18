package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
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
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "search", required = false) String search) {
        // Lấy toàn bộ danh sách từ tầng Service
        List<Task> allTasks = taskService.findAllTasks();

        // Nếu có tham số search, tiến hành lọc gần đúng theo title
        if (search != null && !search.trim().isEmpty()) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getTitle().toLowerCase().contains(search.toLowerCase().trim()))
                    .collect(Collectors.toList());
        }

        // Trả về kết quả bọc trong ResponseEntity với trạng thái 200 OK
        return ResponseEntity.ok(allTasks); // Cách viết ngắn gọn của new ResponseEntity<>(..., HttpStatus.OK)
    }
}