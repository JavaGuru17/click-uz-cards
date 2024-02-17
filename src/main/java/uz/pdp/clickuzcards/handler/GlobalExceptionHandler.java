package uz.pdp.clickuzcards.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.clickuzcards.dto.responce.Response;
import uz.pdp.clickuzcards.exception.AlreadyExistsException;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.exception.NotFoundException;
import uz.pdp.clickuzcards.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return ResponseEntity.ok(new Response(e.getMessage()));
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e) {
        return ResponseEntity.ok(new Response(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.ok(new Response(e.getMessage()));
    }

    @ExceptionHandler(NullOrEmptyException.class)
    public ResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e) {
        return ResponseEntity.ok(new Response(e.getMessage()));
    }
}
