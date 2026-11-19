package ma.enset.customerservice.web;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;
import ma.enset.customerservice.service.CustomerServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {

    private CustomerServiceInterface customerServiceInterface;

    public CustomerRestController(CustomerServiceInterface customerServiceInterface){
        this.customerServiceInterface = customerServiceInterface;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerServiceInterface.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomers(@PathVariable Integer id) throws CustomerNotFoundException {
        return customerServiceInterface.getCustomer(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerServiceInterface.addCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer)
            throws CustomerNotFoundException{
        return customerServiceInterface.updateCustomer(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id){
        customerServiceInterface.deleteCustomer(id);
    }

    @ExceptionHandler
    public String handler(Exception ex){
        return ex.getMessage();
    }
}
