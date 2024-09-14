package crio.learningnavigator.lms.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    private String name;

    @ManyToMany(mappedBy = "enrolledSubject", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Student> registeredStudent;


    public Subject() {
    }

    public Subject(long subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    //Getters && Setters
    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getRegisteredStudent() {
        return registeredStudent;
    }

    public void setRegisteredStudent(List<Student> registeredStudent) {
        this.registeredStudent = registeredStudent;
    }




   

    
}