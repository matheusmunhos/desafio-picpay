package br.com.munhosdev.picpay.service;

import br.com.munhosdev.picpay.controller.dto.TransferDTO;
import br.com.munhosdev.picpay.entity.Transfer;
import br.com.munhosdev.picpay.entity.Wallet;
import br.com.munhosdev.picpay.exception.InsufficientBalanceException;
import br.com.munhosdev.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.munhosdev.picpay.exception.TransferNotAuthorizedException;
import br.com.munhosdev.picpay.exception.WalletNotFoundException;
import br.com.munhosdev.picpay.repository.TransferRepository;
import br.com.munhosdev.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;


    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {

      var sender =  walletRepository.findById(transferDTO.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

      var receive =  walletRepository.findById(transferDTO.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

      validateTransfer(transferDTO,sender);

      sender.debit(transferDTO.value());
      receive.credit(transferDTO.value());


      var transfer = new Transfer(sender, receive, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receive);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transferDTO.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDTO)){
            throw new TransferNotAuthorizedException();
        }
    }
}
