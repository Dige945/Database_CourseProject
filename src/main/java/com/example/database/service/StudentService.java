package com.example.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity<String> enrollCourse(String studentId, String courseId) {
        String sql = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, studentId, courseId);
        return ResponseEntity.ok("选课成功");
    }

    public ResponseEntity<String> addFriend(String student1Id, String student2Id) {
        String sql = "INSERT INTO Friendship (student1_id, student2_id, friendship_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student1Id, student2Id, LocalDateTime.now());
        return ResponseEntity.ok("添加好友成功");
    }

    public ResponseEntity<String> sendMessage(String senderId, String receiverId, String content) {
        String sql = "INSERT INTO Message (sender_student_id, receiver_student_id, content, sent_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, senderId, receiverId, content, LocalDateTime.now());
        return ResponseEntity.ok("消息发送成功");
    }

    public ResponseEntity<String> transfer(String payerId, String payeeId, double amount) {
        String sql = "INSERT INTO TransactionRecord (payer_student_id, payee_student_id, amount, transaction_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, payerId, payeeId, amount, LocalDateTime.now());
        return ResponseEntity.ok("转账成功");
    }

    public ResponseEntity<List<Map<String, Object>>> getEnrollments() {
        String sql = "SELECT * FROM Enrollment";
        List<Map<String, Object>> enrollments = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(enrollments);
    }

    public ResponseEntity<List<Map<String, Object>>> getFriendships() {
        String sql = "SELECT * FROM Friendship";
        List<Map<String, Object>> friendships = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(friendships);
    }

    public ResponseEntity<List<Map<String, Object>>> getMessages() {
        String sql = "SELECT * FROM Message";
        List<Map<String, Object>> messages = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(messages);
    }

    public ResponseEntity<List<Map<String, Object>>> getTransactions() {
        String sql = "SELECT * FROM TransactionRecord";
        List<Map<String, Object>> transactions = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(transactions);
    }
} 