package com.venessa.springwebproject.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Vehicle
{
    @Id
    @SequenceGenerator(name = "VEHICLE_SEQ_GNTR", sequenceName = "VEHICLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_SEQ_GNTR")
    private long vehicleId;
    @Column(name = "type", insertable = false, updatable = false)
    private String type;
    private String vehicleNumber;
    private String model;
    private String brand;
    private LocalDate registrationDate;
    private LocalDate coeExpiry;
    private LocalDate roadTaxExpiry;
    private String fuelType;
    private String transmission;
    private LocalDate soldDate;
    @OneToOne (mappedBy = "vehicle")
    private Transaction transaction;

    public Vehicle() {}

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getCoeExpiry() {
        return coeExpiry;
    }

    public void setCoeExpiry(LocalDate coeExpiry) {
        this.coeExpiry = coeExpiry;
    }

    public LocalDate getRoadTaxExpiry() {
        return roadTaxExpiry;
    }

    public void setRoadTaxExpiry(LocalDate roadTaxExpiry) {
        this.roadTaxExpiry = roadTaxExpiry;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        this.soldDate = soldDate;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
