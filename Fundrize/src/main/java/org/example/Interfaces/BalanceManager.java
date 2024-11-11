package org.example.Interfaces;

import java.math.BigDecimal;

public interface BalanceManager {
    void deposit(BigDecimal amount);
    boolean withdraw(BigDecimal amount);
}