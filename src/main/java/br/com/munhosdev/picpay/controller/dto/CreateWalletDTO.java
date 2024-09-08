package br.com.munhosdev.picpay.controller.dto;

import br.com.munhosdev.picpay.entity.Wallet;
import br.com.munhosdev.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateWalletDTO(

        @NotBlank
        String fullName,
        @NotBlank
        String cpfCnpj,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        WalletType.Enum walletType
) {

    public Wallet toWallet(){
        return new Wallet(
            cpfCnpj,
                email,
                fullName,
                password,
                walletType.get()
        );
    }
}
