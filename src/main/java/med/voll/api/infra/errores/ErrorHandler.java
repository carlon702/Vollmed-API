package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
    return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleErrorDuplicate(IllegalArgumentException e){
        var error = e.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorHandlerValidationIntegridad(Exception e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidationBusiness(Exception e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }



    private record DataErrorValidation(String field, String error){
        public DataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}


