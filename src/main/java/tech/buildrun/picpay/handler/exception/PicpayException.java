package tech.buildrun.picpay.handler.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;

public class PicpayException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("PicPay Internal server error");
        return pb;
    }
    
}
