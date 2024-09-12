package crio.learningnavigator.lms.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExamDto {
    
    private long examId;

    private long subjectId;

    private String subjectName;

    private String examDate;
    
}
