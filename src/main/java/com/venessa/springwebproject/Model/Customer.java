package com.venessa.springwebproject.Model;

import jakarta.persistence.*;

@Entity
public class Customer
{
    @Id
    @SequenceGenerator(name = "CUSTOMER_SEQ_GNTR", sequenceName = "CUSTOMER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ_GNTR")
    private long customerId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String phoneNumber;
    private String email;

    public Customer() {}

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}