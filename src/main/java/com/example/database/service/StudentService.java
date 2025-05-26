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
    }    public ResponseEntity<List<Map<String, Object>>> getEnrollments(String studentId) {
        String sql = "SELECT e.*, c.course_name, c.credits, d.department_name " +
                     "FROM Enrollment e " +
                     "JOIN Course c ON e.course_id = c.course_id " +
                     "JOIN Department d ON c.offering_department_id = d.department_id " +
                     "WHERE e.student_id = ?";
        List<Map<String, Object>> enrollments = jdbcTemplate.queryForList(sql, studentId);
        return ResponseEntity.ok(enrollments);
    }

    public ResponseEntity<List<Map<String, Object>>> getFriendships(String studentId) {
        String sql = "SELECT f.*, s.name as friend_name " +
                     "FROM Friendship f " +
                     "JOIN Student s ON (f.student2_id = s.student_id AND f.student1_id = ?) " +
                     "OR (f.student1_id = s.student_id AND f.student2_id = ?) " +
                     "WHERE f.student1_id = ? OR f.student2_id = ?";
        List<Map<String, Object>> friendships = jdbcTemplate.queryForList(sql, studentId, studentId, studentId, studentId);
        return ResponseEntity.ok(friendships);
    }

    public ResponseEntity<List<Map<String, Object>>> getMessages(String studentId) {
        String sql = "SELECT m.*, " +
                     "sender.name as sender_name, " +
                     "receiver.name as receiver_name " +
                     "FROM Message m " +
                     "JOIN Student sender ON m.sender_student_id = sender.student_id " +
                     "JOIN Student receiver ON m.receiver_student_id = receiver.student_id " +
                     "WHERE m.sender_student_id = ? OR m.receiver_student_id = ? " +
                     "ORDER BY m.sent_time DESC";
        List<Map<String, Object>> messages = jdbcTemplate.queryForList(sql, studentId, studentId);
        return ResponseEntity.ok(messages);
    }

    public ResponseEntity<List<Map<String, Object>>> getTransactions(String studentId) {
        String sql = "SELECT t.*, " +
                     "payer.name as payer_name, " +
                     "payee.name as payee_name " +
                     "FROM TransactionRecord t " +
                     "JOIN Student payer ON t.payer_student_id = payer.student_id " +
                     "JOIN Student payee ON t.payee_student_id = payee.student_id " +
                     "WHERE t.payer_student_id = ? OR t.payee_student_id = ? " +
                     "ORDER BY t.transaction_time DESC";
        List<Map<String, Object>> transactions = jdbcTemplate.queryForList(sql, studentId, studentId);
        return ResponseEntity.ok(transactions);
    }
} 