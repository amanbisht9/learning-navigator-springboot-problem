package crio.learningnavigator.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.lang.String;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> handleStudentRegistrationException(RegistrationException ex, WebRequest request) {
        String causeMessage = (ex.getCause() != null) ? ex.getCause().getMessage() : "No cause available";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), causeMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnrollmentException.class)
    public ResponseEntity<?> handleEnrollmentException(EnrollmentException ex, WebRequest request) {
        String causeMessage = (ex.getCause() != null) ? ex.getCause().getMessage() : "No cause available";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), causeMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FetchDetailsException.class)
    public ResponseEntity<?> handleFetchDetailsException(FetchDetailsException ex, WebRequest request) {
        String causeMessage = (ex.getCause() != null) ? ex.getCause().getMessage() : "No cause available";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), causeMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
}
