package crio.learningnavigator.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crio.learningnavigator.lms.dto.StudentDto;
import crio.learningnavigator.lms.exception.EnrollmentException;
import crio.learningnavigator.lms.exception.FetchDetailsException;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.IStudentRepository;
import crio.learningnavigator.lms.repository.ISubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    @Autowired
    private ISubjectRepository iSubjectRepository;

    public StudentDto registerStudent(String name){
        try {

            if (name == null || name.isEmpty()) {
                throw new RegistrationException("Please enter name", new Throwable("Name cannot be empty or null."));
            }
            
            Student student = new Student();
            student.setName(name);
            student.setEnrolledSubject(new ArrayList<>());
        
            Student saveStudent =  iStudentRepository.save(student);
            return new StudentDto(saveStudent.getRegistrationId(), saveStudent.getName());
            
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage(),e.getCause());
        }

    }

    public Student studentSubjectEnrollment(long studentId, long subjectId) {
        try {
            if(studentId < 1 || subjectId < 1){
                throw new RegistrationException("Please enter correct ids", new Throwable("Ids cannot be less than 1"));
            }

            Optional<Student> student = iStudentRepository.findById(studentId);
            Optional<Subject> subject = iSubjectRepository.findById(subjectId);

            if(!student.isPresent()){
                throw new EnrollmentException("Please enter registered student id", new Throwable("Entered student id is not correct"));
            }

            if(!subject.isPresent()){
                throw new EnrollmentException("Please enter registered subject id", new Throwable("Entered subject id is not correct"));
            }

            //Adding subject
            student.get().getEnrolledSubject().add(subject.get());

            Student saveStudent = iStudentRepository.save(student.get());

            return new Student(saveStudent.getRegistrationId(), saveStudent.getName(), saveStudent.getEnrolledSubject());


        } catch (Exception e) {
            throw new EnrollmentException(e.getMessage(),e.getCause());
        }
    }

    public Student getStudentDetailsById(Long id) {
        try {
            Optional<Student> student = iStudentRepository.findById(id);
            if(!student.isPresent()){
                throw new FetchDetailsException("Please enter correct student id", new Throwable("Entered student id is not registered."));
            }

            return new Student(student.get().getRegistrationId(), student.get().getName(), student.get().getEnrolledSubject(), student.get().getRegisteredExam());

        } catch (Exception e) {
            throw new FetchDetailsException(e.getMessage(),e.getCause());
        }
    }

    public List<Student> getAllStudentDetails() {
        try {
            List<Student> students = iStudentRepository.findAll();
            return students;

        } catch (Exception e) {
            throw new FetchDetailsException(e.getMessage(),e.getCause());
        }
    }
    
}
