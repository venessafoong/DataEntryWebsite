package com.venessa.springwebproject.APILayer;

import com.venessa.springwebproject.Model.Customer;
import com.venessa.springwebproject.Model.Transaction;
import com.venessa.springwebproject.ServiceLayer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer-info")
    public String getCustomerInfo(@Param("keyword") String keyword, Model model)
    {
        List<Customer> customers = customerService.getAll(keyword);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        return "customer-info";
    }

}
