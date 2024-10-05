package tech.buildrun.picpay.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.buildrun.picpay.handler.exception.PicpayException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicpayException.class) // ExceptionHandler annotation is used to handle exceptions in specific handler classes and/or handler methods
    public ProblemDetail handlePicpayException(PicpayException ex) {
        return ex.toProblemDetail(); // toProblemDetail() method is used to return the exception message
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fieldErrors = ex.getFieldErrors()
            .stream()
            .map( f -> new invalidParam(f.getField(), f.getDefaultMessage()))
            .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Your request parameters didn't validate");
        pb.setProperty("invalidParams", fieldErrors);
        return pb;
    }

    private record invalidParam(String name, String message){};

    
}