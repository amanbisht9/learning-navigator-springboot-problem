package crio.learningnavigator.lms.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import crio.learningnavigator.lms.dto.SubjectDto;

import crio.learningnavigator.lms.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/register")
    public ResponseEntity<SubjectDto> registerStudent(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        SubjectDto subject = subjectService.registerSubject(name);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }
    
}
