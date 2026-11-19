package ma.enset.customerservice.service;


import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceInterface {

    public List<Customer> getCustomers();
    public Customer getCustomer(Long id) throws CustomerNotFoundException;
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(Long id, Customer customer) throws CustomerNotFoundException;
    public void deleteCustomer(Long id);
}
