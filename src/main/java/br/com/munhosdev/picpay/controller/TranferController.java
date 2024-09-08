package br.com.munhosdev.picpay.controller;

import br.com.munhosdev.picpay.controller.dto.TransferDTO;
import br.com.munhosdev.picpay.entity.Transfer;
import br.com.munhosdev.picpay.service.TransferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TranferController {

    private final TransferService service;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO dto) {
        var resp = service.transfer(dto);

        return ResponseEntity.ok(resp);
    }
}
