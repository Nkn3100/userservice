package org.scaler.userservice.controlleradvices;

import org.scaler.userservice.dtos.ExceptionDto;
import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.TokenAlreadyExpiredOrNotFoundException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ExceptionDto> handleUserNoFoundException(Exception exception) {
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setMessage("User not found");
            exceptionDto.setDetail(exception.getMessage());
            return ResponseEntity.ok(exceptionDto);
        }
        @ExceptionHandler(InvalidPasswordException.class)
        public ResponseEntity<ExceptionDto> handleInvalidPasswordException(Exception exception) {
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setMessage("Invalid password");
            exceptionDto.setDetail(exception.getMessage());
            return ResponseEntity.ok(exceptionDto);
        }
        @ExceptionHandler(TokenAlreadyExpiredOrNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleTokenAlreadyExpiredOrNotFoundException(Exception exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Token already expired or not found");
        exceptionDto.setDetail(exception.getMessage());
        return ResponseEntity.ok(exceptionDto);
    }
}
