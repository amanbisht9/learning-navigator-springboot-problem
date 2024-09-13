package crio.learningnavigator.lms.dto;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {

    private long examId;

    private Subject subject;

    private List<Student> enrolledStudents;
    
}
