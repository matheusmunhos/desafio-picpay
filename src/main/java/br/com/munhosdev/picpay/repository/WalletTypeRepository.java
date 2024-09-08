package br.com.munhosdev.picpay.repository;

import br.com.munhosdev.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
