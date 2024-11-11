package org.example.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invest_history")
public class InvestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

    @OneToMany(mappedBy = "investHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;

    // Конструктор
    public InvestHistory(Investor investor) {
        this.investor = investor;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    // Метод для добавления займа
    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.setInvestHistory(this); // Устанавливаем связь с текущей InvestHistory
    }

    // Метод для удаления займа
    public void removeLoan(Loan loan) {
        loans.remove(loan);
        loan.setInvestHistory(null); // Удаляем связь с InvestHistory
    }
}

