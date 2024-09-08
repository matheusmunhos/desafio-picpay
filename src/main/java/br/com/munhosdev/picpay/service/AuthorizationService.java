package br.com.munhosdev.picpay.service;

import br.com.munhosdev.picpay.client.AuthorizationClient;
import br.com.munhosdev.picpay.controller.dto.TransferDTO;
import br.com.munhosdev.picpay.entity.Transfer;
import br.com.munhosdev.picpay.exception.PicPayException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public Boolean isAuthorized(TransferDTO transfer) {
        var resp = authorizationClient.isAuthorized();
        if (resp.getStatusCode().isError()){
            throw new PicPayException();
        } else {
            return resp.getBody().authorized();
        }
    }
}
