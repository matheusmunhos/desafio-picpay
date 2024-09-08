package br.com.munhosdev.picpay.service;

import br.com.munhosdev.picpay.controller.dto.CreateWalletDTO;
import br.com.munhosdev.picpay.entity.Wallet;
import br.com.munhosdev.picpay.exception.WalletDataAlreadyExistsException;
import br.com.munhosdev.picpay.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDTO dto) {
        var walletDb =walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(),dto.email());
        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
       return walletRepository.save(dto.toWallet());
    }
}
