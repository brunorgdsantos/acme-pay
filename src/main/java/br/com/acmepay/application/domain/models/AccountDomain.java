package br.com.acmepay.application.domain.models;

import br.com.acmepay.application.domain.exception.BalanceTowithdrawException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDomain {
    private Long id;
    private Integer number;
    private Integer agency;
    private BigDecimal balance; //SaldoConta
    private Boolean close;
    private CustomerDomain customerDomain;
    private List<CardDomain> cardDomains;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<String> transactions = new ArrayList<>();


    public void deposit(BigDecimal amount){ //Valor a ser depositado
        this.balance = this.balance.add(amount);
        this.transactions.add("Deposit succesfully" + amount.toString());
    }

    public void withdraw(BigDecimal amount) throws BalanceTowithdrawException { //Saque
        if(checkBalance(amount)){
            this.balance = this.balance.subtract(amount);
            this.transactions.add("Withdrw succesfully" + amount.toString());
        }else{
            throw new BalanceTowithdrawException("error withdraw");
        }
    }

    public void tranfer(AccountDomain accountDomain, BigDecimal amount) throws BalanceTowithdrawException { //Transferencia
        this.withdraw(amount);
        accountDomain.deposit(amount);
        this.transactions.add("Transfer succesfully" + amount.toString());
    }

    public List<String> extrato(){
        return this.transactions;
    }

    public boolean checkBalance(BigDecimal amount){
        return this.balance.compareTo(amount) >= 0;
    }

    public void create(AccountDomain accountDomain) {
        this.setId(accountDomain.id);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdated_at(null);
        this.setCustomerDomain(null);
        this.setCardDomains(new ArrayList<>());
        this.setBalance(BigDecimal.ZERO);
        this.setNumber(accountDomain.number);
        this.setAgency(accountDomain.agency);
        this.setClose(accountDomain.close);
        this.transactions.add("account created succesfully" + LocalDateTime.now());
    }

}
