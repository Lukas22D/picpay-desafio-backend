package tech.buildrun.picpay.Controller;

import tech.buildrun.picpay.Controller.dto.CreateWalletDto;
import tech.buildrun.picpay.model.Wallet;
import tech.buildrun.picpay.service.WalletService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class WallerController {

    private final WalletService walletService;

    public WallerController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
        Wallet wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
        
    }
    
}
