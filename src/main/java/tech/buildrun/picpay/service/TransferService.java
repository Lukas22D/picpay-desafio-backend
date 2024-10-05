package tech.buildrun.picpay.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tech.buildrun.picpay.Controller.dto.TransferDto;
import tech.buildrun.picpay.handler.exception.InsufficientBalanceException;
import tech.buildrun.picpay.handler.exception.TransferNotAllowedForWalletTypeException;
import tech.buildrun.picpay.handler.exception.TransferNotAuthorizedException;
import tech.buildrun.picpay.handler.exception.WalletNotFoundException;
import tech.buildrun.picpay.model.Transfer;
import tech.buildrun.picpay.model.Wallet;
import tech.buildrun.picpay.repository.TransferRepository;
import tech.buildrun.picpay.repository.WalletRepository;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, 
                           NotificationService notificationService,
                           AuthorizationService authorizationService,  
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer()).orElseThrow( () -> new WalletNotFoundException(transferDto.payee()));
        var receiver = walletRepository.findById(transferDto.payee()).orElseThrow( () -> new WalletNotFoundException(transferDto.payee()));
        
        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());
        var transfer = new Transfer(sender, receiver, transferDto.value());
        walletRepository.save(sender);
        walletRepository.save(receiver);

        var transferResult = transferRepository.save(transfer);
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transfer));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if(!sender.isTransferAllowedForWallet()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalancerEqualOrGreatherThan(transferDto.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }
    }

}
