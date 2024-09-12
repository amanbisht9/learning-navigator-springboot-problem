package crio.learningnavigator.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crio.learningnavigator.lms.dto.SubjectDto;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.ISubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private ISubjectRepository subjectRepository;

    public SubjectDto registerSubject(String name) {
        try {

            if (name == null || name.isEmpty()) {
                throw new RegistrationException("Please enter name", new Throwable("Name cannot be empty or null."));
            }
            
            Subject subject = new Subject();
            subject.setName(name);
        
            Subject saveSubject =  subjectRepository.save(subject);
            return new SubjectDto(saveSubject.getSubjectId(), saveSubject.getName());
            
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage(),e.getCause());
        }
    }
    
}
