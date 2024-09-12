package crio.learningnavigator.lms.dto;

import java.util.ArrayList;
import java.util.List;

import crio.learningnavigator.lms.model.Exam;
import crio.learningnavigator.lms.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private long registrationId;

    private String name;

    private List<Subject> enrolledSubjects;

    private List<Exam> registeredExams;

    public StudentDto(long registrationId, String name) {
        this.registrationId = registrationId;
        this.name = name;
        this.enrolledSubjects = new ArrayList<>();
        this.registeredExams = new ArrayList<>();
    }

    
    
}
