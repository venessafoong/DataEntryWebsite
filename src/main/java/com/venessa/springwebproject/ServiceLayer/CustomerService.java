package com.venessa.springwebproject.ServiceLayer;

import com.venessa.springwebproject.DataAccessLayer.CustomerRepository;
import com.venessa.springwebproject.Model.Customer;
import com.venessa.springwebproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAll(String keyword)
    {
        if (keyword != null)
        {
            return customerRepo.findAll(keyword);
        }
        return customerRepo.findAll();
    }

    public boolean addCustomer(Customer customer)
    {
        Optional<Customer> customerOptional = customerRepo.findByPhoneNumber(customer.getPhoneNumber());

        if(customerOptional.isEmpty()) {
            customerRepo.save(customer);
            return true;
        }else {
            return false;
        }
    }
}
