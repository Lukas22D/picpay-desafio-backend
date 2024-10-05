package tech.buildrun.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletTypeException extends PicpayException {

    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Transfer not allowed for wallet type");
        pb.setDetail("The transfer is not allowed for the wallet type");
        return pb;
    }
    
}
