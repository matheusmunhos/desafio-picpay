package br.com.munhosdev.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistsException extends PicPayException{

    private String detail;

    public WalletDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Wallet Data Already Exists");
        pb.setDetail(detail);
        return pb;
    }
}
