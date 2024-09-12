package crio.learningnavigator.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crio.learningnavigator.lms.dto.StudentDto;

import crio.learningnavigator.lms.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentDto> registerStudent(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        StudentDto student = studentService.registerStudent(name);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // @PostMapping("/suject_enrollment")
    // public String postMethodName(@RequestBody String entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    
}
