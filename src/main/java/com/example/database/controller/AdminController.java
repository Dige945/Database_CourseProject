package com.example.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.database.service.AdminService;
import com.example.database.service.AuthService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AuthService authService;

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestParam String courseId, @RequestParam String courseName, @RequestParam int offeringDepartmentId, @RequestParam double credits) {
        return adminService.addCourse(courseId, courseName, offeringDepartmentId, credits);
    }

    @PostMapping("/addGrade")
    public ResponseEntity<String> addGrade(@RequestParam String studentId, @RequestParam String courseId, @RequestParam double grade) {
        return adminService.addGrade(studentId, courseId, grade);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Map<String, Object>>> getAllCourses() {
        return adminService.getAllCourses();
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Map<String, Object>>> getAllGrades() {
        return adminService.getAllGrades();
    }
    
    @GetMapping("/loginRecords")
    public ResponseEntity<Map<String, Object>> getLoginRecords() {
        return authService.getLoginRecords();
    }
} 