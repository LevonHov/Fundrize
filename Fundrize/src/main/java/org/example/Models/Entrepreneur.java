package org.example.Models;

import org.example.Models.Enums.Role;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "entrepreneurs")
@PrimaryKeyJoinColumn(name = "user_id")
public class Entrepreneur extends User {

    private UserBalanceManager balanceManager;

    @OneToMany(mappedBy = "entrepreneur", cascade = CascadeType.ALL)
    private List<LoanApplication> loanApplications;

    @OneToMany(mappedBy = "entrepreneur", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToOne(mappedBy = "entrepreneur", cascade = CascadeType.ALL)
    private CreditScore creditScore;

    @OneToOne(mappedBy = "entrepreneur", cascade = CascadeType.ALL)
    private CreditHistory creditHistory;

    public Entrepreneur(int id, String firstName, String lastName, String email,
                        String passwordHash, String phone, Role role) {
        super(firstName, lastName, email, passwordHash, phone, role);
        this.balanceManager = new UserBalanceManager();
    }

    public Entrepreneur(){}

    public void deposit(BigDecimal amount) {
        balanceManager.deposit(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        return balanceManager.withdraw(amount);
    }

    public BigDecimal getBalance() {
        return balanceManager.getBalance();
    }
}
