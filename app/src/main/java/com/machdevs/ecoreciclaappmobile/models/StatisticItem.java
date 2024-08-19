package com.machdevs.ecoreciclaappmobile.models;

public class StatisticItem {
    private String categoryName;
    private double averageAmount;
    private double maxAmount;
    private double minAmount;
    private double totalValue;
    private String unit;

    public StatisticItem(String categoryName, double averageAmount, double maxAmount, double minAmount, double totalValue, String unit) {
        this.categoryName = categoryName;
        this.averageAmount = averageAmount;
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.totalValue = totalValue;
        this.unit = unit;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public String getUnit() {
        return unit;
    }
}