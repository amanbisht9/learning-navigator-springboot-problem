package crio.learningnavigator.lms;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import crio.learningnavigator.lms.dto.SubjectDto;
import crio.learningnavigator.lms.exception.RegistrationException;
import crio.learningnavigator.lms.model.Subject;
import crio.learningnavigator.lms.repository.ISubjectRepository;
import crio.learningnavigator.lms.service.SubjectService;

@SpringBootTest
public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private ISubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterSubject_successful() {
        // Arrange
        String subjectName = "Mathematics";
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setSubjectId(1L);  // Example ID after saving

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        // Act
        SubjectDto result = subjectService.registerSubject(subjectName);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getSubjectId());
        assertEquals("Mathematics", result.getName());
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    public void testRegisterSubject_nullName() {
        // Act & Assert
        RegistrationException exception = assertThrows(RegistrationException.class, () -> {
            subjectService.registerSubject(null);
        });

        assertEquals("Please enter name", exception.getMessage());
    }

    @Test
    public void testRegisterSubject_emptyName() {
        // Act & Assert
        RegistrationException exception = assertThrows(RegistrationException.class, () -> {
            subjectService.registerSubject("");
        });

        assertEquals("Please enter name", exception.getMessage());
    }
}
