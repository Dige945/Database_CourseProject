package com.example.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity<String> addCourse(String courseId, String courseName, int offeringDepartmentId, double credits) {
        String sql = "INSERT INTO Course (course_id, course_name, offering_department_id, credits) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, courseId, courseName, offeringDepartmentId, credits);
        return ResponseEntity.ok("课程添加成功");
    }

    public ResponseEntity<String> addGrade(String studentId, String courseId, double grade) {
        String sql = "INSERT INTO Enrollment (student_id, course_id, grade) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, studentId, courseId, grade);
        return ResponseEntity.ok("成绩添加成功");
    }

    public ResponseEntity<List<Map<String, Object>>> getAllCourses() {
        String sql = "SELECT c.course_id as courseId, c.course_name as courseName, " +
                    "d.department_name as teacher, c.credits as credit " +
                    "FROM Course c " +
                    "LEFT JOIN Department d ON c.offering_department_id = d.department_id";
        List<Map<String, Object>> courses = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(courses);
    }

    public ResponseEntity<List<Map<String, Object>>> getAllGrades() {
        String sql = "SELECT e.student_id as studentId, e.course_id as courseId, e.grade " +
                    "FROM Enrollment e " +
                    "WHERE e.grade IS NOT NULL";
        List<Map<String, Object>> grades = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(grades);
    }
} 