package org.example.Models;

import org.example.Models.Enums.LoanStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invest_score")
public class InvestScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

    @Column(nullable = false)
    private BigDecimal score;

    @Column(nullable = false)
    private int successfulLoansCount;

    @Column(nullable = false)
    private BigDecimal totalInvestedAmount;

    // Конструктор
    public InvestScore(Investor investor) {
        this.investor = investor;
        this.score = calculateScore(); // Вычисление оценки при создании
        this.successfulLoansCount = 0;
        this.totalInvestedAmount = BigDecimal.ZERO;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public BigDecimal getScore() {
        return score;
    }

    public int getSuccessfulLoansCount() {
        return successfulLoansCount;
    }

    public BigDecimal getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    // Метод для вычисления оценки инвестора
    public void updateInvestScore() {
        // Пример простого вычисления на основе успешных займов и общего объема инвестиций
        this.score = calculateScore();
    }

    // Пример вычисления оценки
    private BigDecimal calculateScore() {
        // Простой пример: оценка на основе количества успешных займов и общей суммы инвестиций
        BigDecimal baseScore = BigDecimal.valueOf(50); // Базовая оценка
        BigDecimal successMultiplier = BigDecimal.valueOf(10); // Умножитель за успешные займы
        BigDecimal amountMultiplier = BigDecimal.valueOf(0.05); // Умножитель за сумму инвестиций

        BigDecimal totalScore = baseScore.add(BigDecimal.valueOf(successfulLoansCount).multiply(successMultiplier));
        totalScore = totalScore.add(totalInvestedAmount.multiply(amountMultiplier));

        // Оценка не может быть ниже 0
        return totalScore.max(BigDecimal.ZERO);
    }

    // Метод для добавления успешного займа
    public void addSuccessfulLoan(Loan loan) {
        successfulLoansCount++;
        totalInvestedAmount = totalInvestedAmount.add(loan.getAmount());
        updateInvestScore(); // Пересчитываем оценку
    }

    // Метод для обновления информации о займах
    public void updateInvestmentDetails(Loan loan) {
        if (loan.getStatus() == LoanStatus.Completed) {
            addSuccessfulLoan(loan);
        }
    }
}
