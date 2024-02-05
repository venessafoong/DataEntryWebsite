package com.venessa.springwebproject.ServiceLayer;

import com.venessa.springwebproject.DataAccessLayer.CustomerRepository;
import com.venessa.springwebproject.DataAccessLayer.TransactionRepository;
import com.venessa.springwebproject.DataAccessLayer.VehicleRepository;
import com.venessa.springwebproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private VehicleRepository vehicleRepo;
    @Autowired
    private CustomerRepository customerRepo;

    public List<Transaction> getAll(String keyword)
    {
        if (keyword != null)
        {
            return transactionRepo.findAll(keyword);
        }
        return transactionRepo.findAll();
    }

    public boolean addTransaction(long customerId, long vehicleId, LocalDate soldDate, double price)
    {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
        if (vehicleOptional.isPresent())
        {
            Customer customer = customerOptional.orElse(null);
            Vehicle vehicle = vehicleOptional.orElse(null);
            Transaction transaction = new Transaction(customer, vehicle, price, soldDate);
            transactionRepo.save(transaction);
            return true;
        }
        return false;
    }

    public void addTransaction(Customer customer, long vehicleId, LocalDate soldDate, double price)
    {
        Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
        if (vehicleOptional.isPresent())
        {
            Vehicle vehicle = vehicleOptional.orElse(null);
            Transaction transaction = new Transaction(customer, vehicle, price, soldDate);
            transactionRepo.save(transaction);
        }
    }
}
