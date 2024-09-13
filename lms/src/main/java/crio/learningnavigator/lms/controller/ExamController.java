package crio.learningnavigator.lms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crio.learningnavigator.lms.dto.ExamDto;
import crio.learningnavigator.lms.dto.ExamRegisterDto;
import crio.learningnavigator.lms.dto.StudentExamEnrollmentRequest;
import crio.learningnavigator.lms.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/register")
    public ResponseEntity<ExamDto> registerStudent(@RequestBody ExamRegisterDto request) {
        long subjectId = request.getSubjectId();

        ExamDto exam = examService.registerExam(subjectId);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ExamDto> registerStudent(@PathVariable Long id, @RequestBody StudentExamEnrollmentRequest studentExamEnrollmentRequest) {
        long studentId = studentExamEnrollmentRequest.getStudentId();

        ExamDto exam = examService.registerExamForStudent(id,studentId);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }
}
