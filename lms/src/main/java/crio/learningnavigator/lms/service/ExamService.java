package crio.learningnavigator.lms.service;

import crio.learningnavigator.lms.dto.ExamDto;
import crio.learningnavigator.lms.exception.EnrollmentException;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Exam;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.IExamRepository;
import crio.learningnavigator.lms.repository.IStudentRepository;
import crio.learningnavigator.lms.repository.ISubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ISubjectRepository iSubjectRepository;
    @Autowired
    private IExamRepository iExamRepository;
    @Autowired
    private IStudentRepository iStudentRepository;

    public ExamDto registerExam(long subjectId) {
        try {

            Optional<Subject> checkSubject = iSubjectRepository.findById(subjectId);
            

            if(!checkSubject.isPresent()){
                throw new RegistrationException("Please enter correct subject id", new Throwable("Subject id should be registered."));
            }

            
            Exam exam = new Exam();
            exam.setSubject(checkSubject.get());
            exam.setEnrolledStudents(new ArrayList<>());

            Exam saveExam =  iExamRepository.save(exam);
            return new ExamDto(saveExam.getExamId(), saveExam.getSubject(), saveExam.getEnrolledStudents());
            
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage(),e.getCause());
        }
    }

    public ExamDto registerExamForStudent(Long id, long studentId) {

        try {
            if(id < 1 || studentId < 1){
                throw new RegistrationException("Please enter correct ids", new Throwable("Ids cannot be less than 1"));
            }

            Optional<Student> student = iStudentRepository.findById(studentId);
            Optional<Exam> exam = iExamRepository.findById(id);

            if(!student.isPresent()){
                throw new EnrollmentException("Please enter registered student id", new Throwable("Entered student id is not correct"));
            }

            if(!exam.isPresent()){
                throw new EnrollmentException("Please enter registered exam id", new Throwable("Entered exam id is not correct"));
            }
            //Check student's suject list if Exam subject is present or not
            List<Subject> listSubject= student.get().getEnrolledSubject();
            Subject examSubject = exam.get().getSubject();

            boolean isEnrolled = listSubject.stream().anyMatch(subject -> subject.getSubjectId() == examSubject.getSubjectId());

            if(!isEnrolled){
                throw new EnrollmentException("Student cannot register in a exam without enrolling in specific subject.", new Throwable("Enter correct student and exam id"));
            }

            // /Adding Exam
            student.get().getRegisteredExam().add(exam.get());

            iStudentRepository.save(student.get());

            return new ExamDto(exam.get().getExamId(), examSubject, exam.get().getEnrolledStudents());

        } catch (Exception e) {
            throw new EnrollmentException(e.getMessage(),e.getCause());
        }
       
    }
    
}
