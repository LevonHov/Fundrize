package org.example.Models;


import javax.persistence.*;

import org.example.Models.Enums.Role;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "investors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Investor extends User  {
    public UserBalanceManager balanceManager;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public Investor(int id, String firstName, String lastName, String email,
                    String passwordHash, String phone) {
        super(firstName, lastName, email, passwordHash, phone, Role.Investor);
        this.balanceManager = new UserBalanceManager();
    }
    public Investor(){
        super();
    }
    public void deposit(BigDecimal amount){
        balanceManager.deposit(amount);
    }

    public void withdraw(BigDecimal amount){
        balanceManager.withdraw(amount);
    }

    public BigDecimal getBalance(){
        return balanceManager.getBalance();
    }
}
