package crio.learningnavigator.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamRegisterDto {

    private long subjectId;

    private String subjectName;

    private String examDate;

    public ExamRegisterDto(long subjectId, String subjectName, String examDate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.examDate = examDate;
    }

    
    
}
