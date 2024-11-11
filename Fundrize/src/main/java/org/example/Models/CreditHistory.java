package org.example.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "credit_histories")
public class CreditHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "creditHistory", cascade = CascadeType.ALL)
    private List<LoanApplication> loanApplications;

    @OneToOne
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur; // Связь с предпринимателем

    private int totalLoans;
    private int successfulRepayments;
    private int delayedRepayments;
    private LocalDate lastLoanDate;
    private LocalDate lastRepaymentDate;

    // Конструктор
    public CreditHistory(int id, Entrepreneur entrepreneur, int totalLoans,
                         int successfulRepayments, int delayedRepayments,
                         LocalDate lastLoanDate, LocalDate lastRepaymentDate) {
        this.id = id;
        this.entrepreneur = entrepreneur;
        this.totalLoans = totalLoans;
        this.successfulRepayments = successfulRepayments;
        this.delayedRepayments = delayedRepayments;
        this.lastLoanDate = lastLoanDate;
        this.lastRepaymentDate = lastRepaymentDate;
    }

    public CreditHistory() {

    }
    // Геттеры для доступа к полям
    public int getId() {
        return id;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public int getTotalLoans() {
        return totalLoans;
    }

    public int getSuccessfulRepayments() {
        return successfulRepayments;
    }

    public int getDelayedRepayments() {
        return delayedRepayments;
    }

    public LocalDate getLastLoanDate() {
        return lastLoanDate;
    }

    public LocalDate getLastRepaymentDate() {
        return lastRepaymentDate;
    }
}
