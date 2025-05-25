package com.example.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.database.service.StudentService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollCourse(
            @RequestParam(required = true) String studentId,
            @RequestParam(required = true) String courseId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学号不能为空");
        }
        if (courseId == null || courseId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("课程号不能为空");
        }
        return studentService.enrollCourse(studentId, courseId);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(
            @RequestParam(required = true) String student1Id,
            @RequestParam(required = true) String student2Id) {
        if (student1Id == null || student1Id.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学生1学号不能为空");
        }
        if (student2Id == null || student2Id.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学生2学号不能为空");
        }
        return studentService.addFriend(student1Id, student2Id);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(
            @RequestParam(required = true) String senderId,
            @RequestParam(required = true) String receiverId,
            @RequestParam(required = true) String content) {
        if (senderId == null || senderId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("发送人学号不能为空");
        }
        if (receiverId == null || receiverId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("接收人学号不能为空");
        }
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("消息内容不能为空");
        }
        return studentService.sendMessage(senderId, receiverId, content);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam(required = true) String payerId,
            @RequestParam(required = true) String payeeId,
            @RequestParam(required = true) double amount) {
        if (payerId == null || payerId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("付款人学号不能为空");
        }
        if (payeeId == null || payeeId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("收款人学号不能为空");
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("转账金额必须大于0");
        }
        return studentService.transfer(payerId, payeeId, amount);
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<Map<String, Object>>> getEnrollments() {
        return studentService.getEnrollments();
    }

    @GetMapping("/friendships")
    public ResponseEntity<List<Map<String, Object>>> getFriendships() {
        return studentService.getFriendships();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Map<String, Object>>> getMessages() {
        return studentService.getMessages();
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Map<String, Object>>> getTransactions() {
        return studentService.getTransactions();
    }
} 