package tech.buildrun.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicpayException {

    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insufficient balance");
        pb.setDetail("The balance is insufficient for the transfer");
        return pb;
    }
    
}
