package org.example.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "credit_scores")
public class CreditScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "entrepreneur_id")
    private Entrepreneur entrepreneur;

    private int score;
    private LocalDate calculationDate;

    @OneToOne
    @JoinColumn(name = "credit_history_id")
    private CreditHistory creditHistory;

    public CreditScore(Entrepreneur entrepreneur, int score, LocalDate calculationDate,
                       CreditHistory creditHistory) {
        this.entrepreneur = entrepreneur;
        this.score = score;
        this.calculationDate = calculationDate;
        this.creditHistory = creditHistory;
    }


    public int getId() {
        return id;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public int getBalls() {
        return score;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    public CreditHistory getCreditHistory() {
        return creditHistory;
    }
}
