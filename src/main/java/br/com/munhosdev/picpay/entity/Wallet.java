package br.com.munhosdev.picpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_wallet")
public class Wallet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "cpf_cnpj",unique = true)
    private String cpfCnpj;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    public Wallet(String cpfCnpj, String email, String fullName, String password, WalletType walletType) {
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isTransferAllowedForWalletType(){

        return this.walletType.equals(WalletType.Enum.USER.get());
    }

    public boolean isBalancerEqualOrGreaterThan(BigDecimal value) {
        return this.balance.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value) {
     this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
      this.balance = this.balance.add(value);
    }
}
