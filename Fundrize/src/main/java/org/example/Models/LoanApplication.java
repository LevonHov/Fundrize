package org.example.Models;

import org.example.Models.Enums.DescriptionStatus;
import org.example.Models.Enums.LoanAplicationStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int term;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private LoanAplicationStatus status;

    private LocalDate submissionDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "entrepreneur_id")
    private Entrepreneur entrepreneur;

    private DescriptionStatus descriptionStatus;

    @OneToOne(mappedBy = "loanApplication")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "credit_history_id", nullable = false)
    private CreditHistory creditHistory;

    public LoanApplication(int term, BigDecimal amount,Entrepreneur entrepreneur, String description){

        this.term = term;
        this.amount = amount;
        this.entrepreneur = entrepreneur;
        this.submissionDate = LocalDate.now();
        this.status = LoanAplicationStatus.Pending;
        this.description = description;
        this.descriptionStatus = DescriptionStatus.Unchanged;
    }

    public LoanApplication(){}
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

    public LoanAplicationStatus getStatus() {
        return status;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public String getDescription() {
        return description;
    }
    // Сеттер
    public void setDescription(String description){
        this.description = description;
        this.descriptionStatus = DescriptionStatus.Changed;
    }

    // Методы
    public void approve() {
        this.status = LoanAplicationStatus.Approved;
    }

    public void reject() {
        this.status = LoanAplicationStatus.Rejected;
    }

    public CreditHistory getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(CreditHistory creditHistory) {
        this.creditHistory = creditHistory;
    }
}
