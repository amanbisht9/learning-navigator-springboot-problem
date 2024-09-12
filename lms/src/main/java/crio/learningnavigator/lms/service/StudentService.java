package crio.learningnavigator.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crio.learningnavigator.lms.dto.StudentDto;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.repository.IStudentRepository;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    public StudentDto registerStudent(String name){
        try {

            if (name == null || name.isEmpty()) {
                throw new RegistrationException("Please enter name", new Throwable("Name cannot be empty or null."));
            }
            
            Student student = new Student();
            student.setName(name);
        
            Student saveStudent =  iStudentRepository.save(student);
            return new StudentDto(saveStudent.getRegistrationId(), saveStudent.getName());
            
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage(),e.getCause());
        }

    }
    
}
