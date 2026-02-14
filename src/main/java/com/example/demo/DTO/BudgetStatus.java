package com.example.demo.DTO;

import java.util.Map;

public class BudgetStatus {
    private Double totalLimit;
    private Double spentAmount;
    private Double remainingAmount;
    private Double spentPercentage;
    private Map<String, Double> categoryTotals;

    public Double getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Double totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Double getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(Double spentAmount) {
        this.spentAmount = spentAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Double getSpentPercentage() {
        return spentPercentage;
    }

    public void setSpentPercentage(Double spentPercentage) {
        this.spentPercentage = spentPercentage;
    }

    public Map<String, Double> getCategoryTotals() {
        return categoryTotals;
    }

    public void setCategoryTotals(Map<String, Double> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }
}
