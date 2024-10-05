package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;

import tech.buildrun.picpay.Controller.dto.CreateWalletDto;
import tech.buildrun.picpay.handler.exception.WalletDataAlreadyExist;
import tech.buildrun.picpay.model.Wallet;
import tech.buildrun.picpay.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        // Check if wallet already exists
        walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email()) // Check if wallet already exists
        .ifPresent(wallet -> {
            throw new WalletDataAlreadyExist("Wallet already exists"); // If wallet or email is already in use, throw an exception
        });

        return walletRepository.save(dto.toWallet());
    }
    
}
