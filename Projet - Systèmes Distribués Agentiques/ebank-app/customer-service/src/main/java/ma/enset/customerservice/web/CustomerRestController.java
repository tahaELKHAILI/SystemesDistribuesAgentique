package ma.enset.customerservice.web;

import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;
import ma.enset.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable String id) throws CustomerNotFoundException {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(Customer customer){
        return customerService.addCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable String id, Customer customer) throws CustomerNotFoundException{
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
    }

    @ExceptionHandler
    public String exceptionhandler(Exception exception){
        return exception.getMessage();
    }

}
