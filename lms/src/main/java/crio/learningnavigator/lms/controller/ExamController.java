package crio.learningnavigator.lms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crio.learningnavigator.lms.dto.ExamDto;
import crio.learningnavigator.lms.dto.ExamRegisterDto;

import crio.learningnavigator.lms.service.ExamService;

import java.lang.String;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/register")
    public ResponseEntity<ExamDto> registerStudent(@RequestBody ExamRegisterDto request) {
        long subjectId = request.getSubjectId();
        String subjectName = request.getSubjectName();
        String examDate = request.getExamDate();

        ExamDto exam = examService.registerExam(subjectId,subjectName,examDate);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }
}
