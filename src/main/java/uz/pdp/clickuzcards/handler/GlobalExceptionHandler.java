package uz.pdp.clickuzcards.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.clickuzcards.dto.responce.CustomResponseEntity;
import uz.pdp.clickuzcards.exception.AlreadyExistsException;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.exception.NotFoundException;
import uz.pdp.clickuzcards.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsException.class)
    public CustomResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public CustomResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e) {
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public CustomResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(NullOrEmptyException.class)
    public CustomResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e) {
        return CustomResponseEntity.badRequest(e.getMessage());
    }
}
