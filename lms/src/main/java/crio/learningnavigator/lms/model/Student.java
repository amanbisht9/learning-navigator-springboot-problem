package crio.learningnavigator.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long registrationId;

    private String name;

    // private Subject enrolledSubject;

    // private Exam registeredExam;


    public Long getRegistrationId() {
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

    // public Subject getEnrolledSubject() {
    //     return enrolledSubject;
    // }

    // public void setEnrolledSubject(Subject enrolledSubject) {
    //     this.enrolledSubject = enrolledSubject;
    // }

    // public Exam getRegisteredExam() {
    //     return registeredExam;
    // }

    // public void setRegisteredExam(Exam registeredExam) {
    //     this.registeredExam = registeredExam;
    // }


   
}