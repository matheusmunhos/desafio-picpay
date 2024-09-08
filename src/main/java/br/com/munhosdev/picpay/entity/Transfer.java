package br.com.munhosdev.picpay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "waller_sender_id")
    @ManyToOne
    private Wallet sender;

    @JoinColumn(name = "wallet_receive_id")
    @ManyToOne
    private Wallet receiver;

    @Column(name = "value")
    private BigDecimal value;

    public Transfer(Wallet receiver, Wallet sender, BigDecimal value) {
        this.receiver = receiver;
        this.sender = sender;
        this.value = value;
    }
}
