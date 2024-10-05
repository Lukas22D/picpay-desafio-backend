package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;

import tech.buildrun.picpay.Controller.dto.TransferDto;
import tech.buildrun.picpay.client.AuthorizationClient;
import tech.buildrun.picpay.handler.exception.TransferNotAuthorizedException;

@Service
public class AuthorizationService {
    
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {
        var responseEntity = authorizationClient.isAuthorized();
        var response = responseEntity.getBody();
    
        // Verifique se a resposta foi recebida corretamente
        if (responseEntity.getStatusCode().isError() || response == null) {
            throw new TransferNotAuthorizedException();
        }
    
        // Retorne o valor de autorização
        return response.getData().isAuthorization();
    }
}
