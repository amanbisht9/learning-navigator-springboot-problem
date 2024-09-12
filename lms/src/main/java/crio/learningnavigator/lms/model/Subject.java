package crio.learningnavigator.lms.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    private String name;

    
    // private Student registeredStudent;

 
    // private Exam exam;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public Student getRegisteredStudent() {
    //     return registeredStudent;
    // }

    // public void setRegisteredStudent(Student registeredStudent) {
    //     this.registeredStudent = registeredStudent;
    // }

    // public Exam getExam() {
    //     return exam;
    // }

    // public void setExam(Exam exam) {
    //     this.exam = exam;
    // }

    
}