package com.machdevs.ecoreciclaappmobile.models;

import java.io.Serializable;
import java.util.Date;

public class RecyclingEntry implements Serializable {
    private RecyclingCategory category;
    private double amount;
    private double value;
    private Date date;

    public RecyclingEntry(RecyclingCategory category, double amount, double value, Date date) {
        this.category = category;
        this.amount = amount;
        this.value = value;
        this.date = date;
    }

    public RecyclingCategory getCategory() {
        return category;
    }

    public void setCategory(RecyclingCategory category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecyclingEntry{" +
                "category=" + category +
                ", amount=" + amount +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
