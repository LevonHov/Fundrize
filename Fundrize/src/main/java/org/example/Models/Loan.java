package org.example.Models;

import org.example.Models.Enums.LoanStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;
    private int term; // Срок займа в месяцах
    private int percent; // Процентная ставка
    private LocalDate issueDate;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @OneToOne
    @JoinColumn(name = "loan_application_id")
    private LoanApplication loanApplication;

    @ManyToOne
    @JoinColumn(name = "invest_history_id", nullable = false)
    private InvestHistory investHistory;

    private String description;

    public Loan(BigDecimal amount, int term, int percent, Investor investor, String description) {
        this.amount = amount;
        this.term = term;
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Процент должен быть в диапазоне от 0 до 100.");
        }
        this.percent = percent;
        this.issueDate = LocalDate.now();
        this.expirationDate = issueDate.plusMonths(term);
        this.status = LoanStatus.Active;
        this.investor = investor;
        this.description = description;
    }

    public Loan(){}


    // Геттеры
    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getTerm() {
        return term;
    }

    public int getPercent() {
        return percent;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public Investor getInvestor() {
        return investor;
    }

    public String getDescription() {
        return description;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public InvestHistory getInvestHistory() {
        return investHistory;
    }

    public void setInvestHistory(InvestHistory investHistory) {
        this.investHistory = investHistory;
    }

    // Методы для изменения статуса
    public void complete() {
        if (this.status != LoanStatus.Active) {
            throw new IllegalStateException("Займ может быть завершен только в активном состоянии.");
        }
        this.status = LoanStatus.Completed;
    }

    public void cancel() {
        if (this.status != LoanStatus.Active) {
            throw new IllegalStateException("Займ может быть отменен только в активном состоянии.");
        }
        this.status = LoanStatus.Canceled;
    }
}
