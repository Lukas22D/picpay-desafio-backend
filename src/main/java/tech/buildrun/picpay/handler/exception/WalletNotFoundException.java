package tech.buildrun.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicpayException {
    
    private Long walletId;

    public WalletNotFoundException(Long walletId){
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Wallet not found");
        pb.setDetail("The wallet you are looking for does not exist with id: " + walletId + ".");
        return pb;
    }
    
}
