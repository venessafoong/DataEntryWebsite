package com.venessa.springwebproject.Model;

import jakarta.persistence.*;

@Entity
public class User
{
    @Id
    @SequenceGenerator(name = "USER_SEQ_GNTR", sequenceName = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GNTR")
    private long userId;
    @Column(unique = true)
    private String username;
    private String password;

    public User() {}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
