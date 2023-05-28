package com.ZikriTest.Controller;

import com.ZikriTest.Entity.Customer;
import com.ZikriTest.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/new-customer/{id}")
    public String newCustomer(@RequestBody Customer customer, @PathVariable("id") Long id){
        return customerService.newCustomer(customer, id);
    }

    @GetMapping("/get-all-customer")
    public List<Customer> getAllItem(){
        return customerService.readAllCustomer();

    }

    @DeleteMapping("/delete-customer/{id}")
    public String deleteItem(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return "Sukses hapus";
    }
}
