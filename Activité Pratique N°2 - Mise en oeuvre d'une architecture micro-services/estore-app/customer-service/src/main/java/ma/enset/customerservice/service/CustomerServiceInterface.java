package ma.enset.customerservice.service;


import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceInterface {

    public List<Customer> getCustomers();
    public Customer getCustomer(Integer id) throws CustomerNotFoundException;
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(Integer id, Customer customer) throws CustomerNotFoundException;
    public void deleteCustomer(Integer id);
}
