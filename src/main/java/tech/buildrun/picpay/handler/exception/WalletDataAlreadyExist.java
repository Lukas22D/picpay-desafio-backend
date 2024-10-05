package tech.buildrun.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExist extends PicpayException {

    private  String detail;

    public WalletDataAlreadyExist(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Wallet data already exist");
        pb.setDetail(detail);
        return pb;
    }
    
}
