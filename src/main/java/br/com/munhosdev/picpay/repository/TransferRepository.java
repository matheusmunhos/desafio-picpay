package br.com.munhosdev.picpay.repository;


import br.com.munhosdev.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
