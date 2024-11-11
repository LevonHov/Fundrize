package org.example.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "entrepreneur_id")
    private Entrepreneur entrepreneur;

    public Payment(BigDecimal amount, LocalDate date, int loanId, Loan loan,
                   Investor investor, Entrepreneur entrepreneur) {
        this.amount = amount;
        this.date = date;
        this.loan = loan;
        this.investor = investor;
        this.entrepreneur = entrepreneur;
    }

    public Payment(){}

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Loan getLoan() {
        return loan;
    }

    public Investor getInvestor() {
        return investor;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }
}
