package com.venessa.springwebproject.DataAccessLayer;

import com.venessa.springwebproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>
{
    @Query("SELECT t, c, v FROM Transaction t " +
            "JOIN t.customer c " +
            "JOIN t.vehicle v " +
            "WHERE CONCAT(c.firstName, ' ', c.lastName, ' ', c.phoneNumber, ' ', v.vehicleNumber, ' ', v.brand, ' ', v.model, ' ',  t.price, ' ', t.date) LIKE %?1%")
    public List<Transaction> findAll(String keyword);

}
