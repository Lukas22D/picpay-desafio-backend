package tech.buildrun.picpay.Controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.buildrun.picpay.model.Wallet;
import tech.buildrun.picpay.model.WalletType;

public record CreateWalletDto(
    @NotBlank String fullName,
    @NotBlank String cpfCnpj,
    @NotBlank String email,
    @NotBlank String password,
    @NotNull WalletType.Enum walletType
) {
   /*
    * This method is used to convert the CreateWalletDto into a Wallet object
    */
    public Wallet toWallet() {
        return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
    }
    
}
