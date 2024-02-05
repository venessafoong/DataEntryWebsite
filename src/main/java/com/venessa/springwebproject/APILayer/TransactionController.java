package com.venessa.springwebproject.APILayer;

import com.venessa.springwebproject.Model.Customer;
import com.venessa.springwebproject.Model.Transaction;
import com.venessa.springwebproject.Model.Vehicle;
import com.venessa.springwebproject.ServiceLayer.CustomerService;
import com.venessa.springwebproject.ServiceLayer.TransactionService;
import com.venessa.springwebproject.ServiceLayer.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/transactions")
    public String getTransactionInfo(@Param("keyword") String keyword, Model model)
    {
        List<Transaction> transactions = transactionService.getAll(keyword);
        model.addAttribute("transactions", transactions);
        model.addAttribute("keyword", keyword);
        return "transaction-info";
    }

    @GetMapping("/transactions-add.new")
    public String goToAddTransactionPage(Model model)
    {
        model.addAttribute("customer", new Customer());
        List<Vehicle> vehicles = vehicleService.getAll(null);
        model.addAttribute("vehicles", vehicles);
        return "add-transaction";
    }

    @PostMapping("/transactions-add.new")
    public String addTransactionForNewCustomer(@RequestParam long vehicleId, @RequestParam LocalDate soldDate, @RequestParam double price, Customer customer, Model model, RedirectAttributes redirectAttributes)
    {
        if (customerService.addCustomer(customer))
        {
            if (vehicleService.updateSoldDate(vehicleId, soldDate))
            {
                transactionService.addTransaction(customer, vehicleId, soldDate, price);
                redirectAttributes.addFlashAttribute("success","Transaction added successfully");
                return "redirect:/transactions";
            } else
            {
                model.addAttribute("error","Not able to add transaction");
                return "add-transaction";
            }
        }
        model.addAttribute("error","Not able to add transaction");
        return "add-transaction";
    }

    @GetMapping("/transactions-add.existing")
    public String goToAddTransactionForExistingCustomerPage(Model model)
    {
        model.addAttribute("transaction", new Transaction());
        List<Vehicle> vehicles = vehicleService.getAll(null);
        model.addAttribute("vehicles", vehicles);
        List<Customer> customers = customerService.getAll(null);
        model.addAttribute("customers", customers);
        return "add-transaction-existing";
    }

    @PostMapping("/transactions-add.existing")
    public String addTransactionForExistingCustomer(@RequestParam long vehicleId, @RequestParam long customerId, @RequestParam LocalDate soldDate, @RequestParam double price, Model model, RedirectAttributes redirectAttributes)
    {
        if (transactionService.addTransaction(customerId, vehicleId, soldDate, price) && vehicleService.updateSoldDate(vehicleId, soldDate))
        {
            redirectAttributes.addFlashAttribute("success","Transaction added successfully");
            return "redirect:/transactions";
        }
        model.addAttribute("error","Not able to add transaction");
        return "add-transaction-existing";
    }

}
