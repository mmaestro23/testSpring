package com.test;

import com.test.Model.Customer;
import com.test.Repo.CustomerRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepo customerRepo;

    public Main(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age){

    }

    record UpdateCustomerRequest(String name, String email, Integer age){

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepo.save(customer);
    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepo.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody UpdateCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepo.save(customer);
    }
}
