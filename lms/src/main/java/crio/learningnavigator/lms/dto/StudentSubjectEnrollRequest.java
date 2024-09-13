package crio.learningnavigator.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectEnrollRequest {

    private long studentId;
    private long subjectId;
    
}
