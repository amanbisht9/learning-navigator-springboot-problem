package crio.learningnavigator.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crio.learningnavigator.lms.dto.StudentDto;
import crio.learningnavigator.lms.dto.StudentSubjectEnrollRequest;
import crio.learningnavigator.lms.dto.SubjectDto;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.service.StudentService;
import java.util.List;

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

    @PostMapping("/subject_enrollment")
    public ResponseEntity<Student> subjectEnrollment(@RequestBody StudentSubjectEnrollRequest studentSubjectEnrollRequest) {
        long studentId = studentSubjectEnrollRequest.getStudentId();
        long subjectId = studentSubjectEnrollRequest.getSubjectId();
        Student student = studentService.studentSubjectEnrollment(studentId, subjectId);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/exam_enrollment")
    public ResponseEntity<Student> examEnrollment(@RequestBody StudentSubjectEnrollRequest studentSubjectEnrollRequest) {
        long studentId = studentSubjectEnrollRequest.getStudentId();
        long subjectId = studentSubjectEnrollRequest.getSubjectId();
        Student student = studentService.studentSubjectEnrollment(studentId, subjectId);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentDetailsById(@PathVariable Long id) {

        Student student = studentService.getStudentDetailsById(id);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudentDetails() {

        List<Student> students = studentService.getAllStudentDetails();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }
    
}
