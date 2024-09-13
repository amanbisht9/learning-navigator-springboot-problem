package crio.learningnavigator.lms.model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long registrationId;

    private String name;

    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_subjects",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @JsonManagedReference
    private List<Subject> enrolledSubject;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_exams",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    @JsonManagedReference
    private List<Exam> registeredExam;


    

    public Student(long registrationId, String name, List<Subject> enrolledSubject, List<Exam> registeredExam) {
        this.registrationId = registrationId;
        this.name = name;
        this.enrolledSubject = enrolledSubject;
        this.registeredExam = registeredExam;
    }

    public Student() {
    }

    public Student(long registrationId, String name, List<Subject> enrolledSubject) {
        this.registrationId = registrationId;
        this.name = name;
        this.enrolledSubject = enrolledSubject;
        this.registeredExam = new ArrayList<>();
    }



    //Getters && Setters
    public long getRegistrationId() {
        return registrationId;
    }



    public void setRegistrationId(long registrationId) {
        this.registrationId = registrationId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public List<Subject> getEnrolledSubject() {
        return enrolledSubject;
    }



    public void setEnrolledSubject(List<Subject> enrolledSubject) {
        this.enrolledSubject = enrolledSubject;
    }



    public List<Exam> getRegisteredExam() {
        return registeredExam;
    }



    public void setRegisteredExam(List<Exam> registeredExam) {
        this.registeredExam = registeredExam;
    }

    


   
}