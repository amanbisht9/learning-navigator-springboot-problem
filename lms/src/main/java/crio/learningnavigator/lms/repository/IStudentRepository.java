package crio.learningnavigator.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crio.learningnavigator.lms.model.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long>{
    
}
