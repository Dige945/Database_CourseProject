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
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String studentId,
            @RequestParam(required = true) String courseId) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudentId = headerStudentId != null ? headerStudentId : studentId;
        
        if (actualStudentId == null || actualStudentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学号不能为空");
        }
        if (courseId == null || courseId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("课程号不能为空");
        }
        return studentService.enrollCourse(actualStudentId, courseId);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String student1Id,
            @RequestParam(required = true) String student2Id) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudent1Id = headerStudentId != null ? headerStudentId : student1Id;
            
        if (actualStudent1Id == null || actualStudent1Id.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学生1学号不能为空");
        }
        if (student2Id == null || student2Id.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("学生2学号不能为空");
        }
        return studentService.addFriend(actualStudent1Id, student2Id);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String senderId,
            @RequestParam(required = true) String receiverId,
            @RequestParam(required = true) String content) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualSenderId = headerStudentId != null ? headerStudentId : senderId;
            
        if (actualSenderId == null || actualSenderId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("发送人学号不能为空");
        }
        if (receiverId == null || receiverId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("接收人学号不能为空");
        }
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("消息内容不能为空");
        }
        return studentService.sendMessage(actualSenderId, receiverId, content);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String payerId,
            @RequestParam(required = true) String payeeId,
            @RequestParam(required = true) double amount) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualPayerId = headerStudentId != null ? headerStudentId : payerId;
            
        if (actualPayerId == null || actualPayerId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("付款人学号不能为空");
        }
        if (payeeId == null || payeeId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("收款人学号不能为空");
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("转账金额必须大于0");
        }
        return studentService.transfer(actualPayerId, payeeId, amount);
    }    @GetMapping("/enrollments")
    public ResponseEntity<List<Map<String, Object>>> getEnrollments(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String studentId) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudentId = headerStudentId != null ? headerStudentId : studentId;
        
        if (actualStudentId == null || actualStudentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        return studentService.getEnrollments(actualStudentId);
    }

    @GetMapping("/friendships")
    public ResponseEntity<List<Map<String, Object>>> getFriendships(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String studentId) {
        
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudentId = headerStudentId != null ? headerStudentId : studentId;
        
        if (actualStudentId == null || actualStudentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        return studentService.getFriendships(actualStudentId);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Map<String, Object>>> getMessages(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String studentId) {
            
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudentId = headerStudentId != null ? headerStudentId : studentId;
        
        if (actualStudentId == null || actualStudentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        return studentService.getMessages(actualStudentId);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Map<String, Object>>> getTransactions(
            @RequestHeader(value = "X-Student-ID", required = false) String headerStudentId,
            @RequestParam(required = false) String studentId) {
            
        // 优先使用header中的学生ID，如果没有则使用参数中的
        String actualStudentId = headerStudentId != null ? headerStudentId : studentId;
        
        if (actualStudentId == null || actualStudentId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        return studentService.getTransactions(actualStudentId);
    }
} 