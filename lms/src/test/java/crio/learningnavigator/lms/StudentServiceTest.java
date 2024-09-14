package crio.learningnavigator.lms;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import crio.learningnavigator.lms.dto.StudentDto;
import crio.learningnavigator.lms.exception.EnrollmentException;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Student;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.IStudentRepository;
import crio.learningnavigator.lms.repository.ISubjectRepository;
import crio.learningnavigator.lms.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private IStudentRepository iStudentRepository;

    @Mock
    private ISubjectRepository iSubjectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterStudent_successful() {
        // Arrange
        String studentName = "John";
        Student student = new Student();
        student.setName(studentName);
        student.setRegistrationId(1L);

        when(iStudentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        StudentDto result = studentService.registerStudent(studentName);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getRegistrationId());
        assertEquals(studentName, result.getName());
        verify(iStudentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testRegisterStudent_nullName() {
        // Act & Assert
        RegistrationException exception = assertThrows(RegistrationException.class, () -> {
            studentService.registerStudent(null);
        });

        assertEquals("Please enter name", exception.getMessage());
    }

    @Test
    public void testRegisterStudent_emptyName() {
        // Act & Assert
        RegistrationException exception = assertThrows(RegistrationException.class, () -> {
            studentService.registerStudent("");
        });

        assertEquals("Please enter name", exception.getMessage());
    }



    @Test
public void testStudentSubjectEnrollment_successful() {
    // Arrange
    long studentId = 1L;
    long subjectId = 1L;
    Student student = new Student(studentId, "John", new ArrayList<>());
    Subject subject = new Subject(subjectId, "Math");

    when(iStudentRepository.findById(studentId)).thenReturn(Optional.of(student));
    when(iSubjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
    when(iStudentRepository.save(any(Student.class))).thenReturn(student);

    // Act
    Student result = studentService.studentSubjectEnrollment(studentId, subjectId);

    // Assert
    assertNotNull(result);
    assertEquals(studentId, result.getRegistrationId());
    assertTrue(result.getEnrolledSubject().contains(subject));
    verify(iStudentRepository, times(1)).findById(studentId);
    verify(iSubjectRepository, times(1)).findById(subjectId);
    verify(iStudentRepository, times(1)).save(any(Student.class));
}

    @Test
    public void testStudentSubjectEnrollment_invalidIds() {
        // Act & Assert
        EnrollmentException exception = assertThrows(EnrollmentException.class, () -> {
            studentService.studentSubjectEnrollment(0, 0);
        });

        assertEquals("Please enter correct ids", exception.getMessage());
    }

    @Test
    public void testStudentSubjectEnrollment_studentNotFound() {
        // Arrange
        long studentId = 1L;
        long subjectId = 1L;
        Subject subject = new Subject(subjectId, "Math");

        when(iStudentRepository.findById(studentId)).thenReturn(Optional.empty());
        when(iSubjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // Act & Assert
        EnrollmentException exception = assertThrows(EnrollmentException.class, () -> {
            studentService.studentSubjectEnrollment(studentId, subjectId);
        });

        assertEquals("Please enter registered student id", exception.getMessage());
    }

    @Test
    public void testStudentSubjectEnrollment_subjectNotFound() {
        // Arrange
        long studentId = 1L;
        long subjectId = 1L;
        Student student = new Student(studentId, "John", new ArrayList<>());

        when(iStudentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(iSubjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // Act & Assert
        EnrollmentException exception = assertThrows(EnrollmentException.class, () -> {
            studentService.studentSubjectEnrollment(studentId, subjectId);
        });

        assertEquals("Please enter registered subject id", exception.getMessage());
    }

}
