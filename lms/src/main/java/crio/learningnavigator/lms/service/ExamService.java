package crio.learningnavigator.lms.service;

import crio.learningnavigator.lms.dto.ExamDto;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Exam;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.IExamRepository;
import crio.learningnavigator.lms.repository.ISubjectRepository;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ISubjectRepository iSubjectRepository;
    @Autowired
    private IExamRepository iExamRepository;

    public ExamDto registerExam(long subjectId, String subjectName, String examDate) {
        try {

            if (subjectId < 1) {
                throw new RegistrationException("Please enter correct subject id.", new Throwable("Subject Id should be 1 or greater."));
            }

            if (subjectName == null || subjectName.isEmpty()) {
                throw new RegistrationException("Please enter subject name", new Throwable("Subject name cannot be empty or null."));
            }

            if (!Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/([0-9]{4})$").matcher(examDate).matches()) {
                throw new RegistrationException("Please enter correct date", new Throwable("Date must be in the format DD/MM/YYYY"));
            }


            //check subject name and id exist in "subject" table

            Optional<Subject> subject = iSubjectRepository.findById(subjectId);

            if(!subject.isPresent()){
                throw new RegistrationException("Please enter correct subject id.", new Throwable("Subject Id should be present."));
            }

            if(!subject.get().getName().equals(subjectName)){
                throw new RegistrationException("Please enter correct subject name", new Throwable("Subject name should be present with corresponding subject id."));
            }

            
            Exam exam = new Exam();
            exam.setSubjectId(subjectId);
            exam.setSubjectName(subjectName);
            exam.setExamDate(examDate);

            Exam saveExam =  iExamRepository.save(exam);
            return new ExamDto(saveExam.getExamId(), saveExam.getSubjectId(), saveExam.getSubjectName(), saveExam.getExamDate());
            
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage(),e.getCause());
        }
    }
    
}
