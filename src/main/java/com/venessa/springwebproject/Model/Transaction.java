package com.venessa.springwebproject.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction
{
    @Id
    @SequenceGenerator(name = "TRANSACTION_SEQ_GNTR", sequenceName = "TRANSACTION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ_GNTR")
    private long transactionId;
    @ManyToOne
    @JoinColumn (name = "fk_customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn (name = "fk_vehicle_id")
    private Vehicle vehicle;
    private double price;
    private LocalDate date;

    public Transaction() {}

    public Transaction(Customer customer, Vehicle vehicle, double price, LocalDate date) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.price = price;
        this.date = date;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
