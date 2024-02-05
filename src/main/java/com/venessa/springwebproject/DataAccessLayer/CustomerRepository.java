package com.venessa.springwebproject.DataAccessLayer;

import com.venessa.springwebproject.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
    @Query("SELECT c FROM Customer c WHERE CONCAT(c.customerId, ' ', c.firstName, ' ', c.lastName, ' ', c.companyName, ' ', c.phoneNumber, ' ', c.email) LIKE %?1%")
    public List<Customer> findAll(String keyword);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
