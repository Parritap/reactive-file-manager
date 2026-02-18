package venus.filemanager.exceptions;


import lombok.NonNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllersExceptionsHandler {


//    @ExceptionHandler(DuplicateKeyException.class)
//    ResponseEntity<@NonNull String> duplicateKey (Exception e) {
//        return ResponseEntity.badRequest().body( "Recurso duplicado");
//    }
}
