package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(params = "studentNumber")
    public ResponseEntity<Student> getStudentByStudentNumber(@RequestParam String studentNumber) {
        return studentService.findByStudentNumber(studentNumber);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping(value = "/orderByGpa")
    public ResponseEntity<List<Student>> findAllByOrderByGpaDesc() {
        return studentService.findAllByOrderByGpaDesc();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping(value = "/{studentNumber}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String studentNumber) {
        return studentService.deleteStudent(studentNumber);
    }
}