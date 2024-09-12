package crio.learningnavigator.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crio.learningnavigator.lms.model.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject,Long>{
    
}
