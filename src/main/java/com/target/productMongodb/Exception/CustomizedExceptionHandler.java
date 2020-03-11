package com.target.productMongodb.Exception;

import com.target.productMongodb.domain.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.PublicKey;
import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDetails> DataNotFoundExceptionHandler(DataNotFoundException e){
        ErrorDetails errorDetails= new ErrorDetails(e.getMessage(),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetails> handleAllExceptions(Exception e){
            ErrorDetails errorDetails= new ErrorDetails(e.getMessage(),new Date());
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
