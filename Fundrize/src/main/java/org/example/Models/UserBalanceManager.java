package org.example.Models;

import org.example.Interfaces.BalanceManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalanceManager implements BalanceManager {
    private BigDecimal balance;

    public UserBalanceManager() {
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount).setScale(2, RoundingMode.HALF_EVEN);
        } else {
            throw new IllegalArgumentException("Сумма депозита должна быть больше нуля");
        }
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть больше нуля");
        }
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_EVEN);
            return true;
        } else {
            System.out.println("Недостаточно средств для снятия");
            return false;
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
