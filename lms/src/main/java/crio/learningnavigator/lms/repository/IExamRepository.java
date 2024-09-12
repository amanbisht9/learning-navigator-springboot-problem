package crio.learningnavigator.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crio.learningnavigator.lms.model.Exam;
import java.util.List;


@Repository
public interface IExamRepository extends JpaRepository<Exam, Long> {
    
}
