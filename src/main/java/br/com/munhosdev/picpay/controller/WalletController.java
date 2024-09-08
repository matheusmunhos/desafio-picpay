package br.com.munhosdev.picpay.controller;

import br.com.munhosdev.picpay.controller.dto.CreateWalletDTO;
import br.com.munhosdev.picpay.entity.Wallet;
import br.com.munhosdev.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO dto) {
        var wallet =  walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }
}
