package crio.learningnavigator.lms.dto;

import crio.learningnavigator.lms.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private long subjectId;
    private String name;
    private List<Student> registeredStudent;


    public SubjectDto(long subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
        this.registeredStudent = new ArrayList<>();
    }
}
