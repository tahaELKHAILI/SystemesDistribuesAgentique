package ma.enset.customerservice.service;


import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(String id, Customer customer) throws CustomerNotFoundException;
    public void deleteCustomer(String id);
    public List<Customer> getCustomers();
    public Customer getCustomer(String id) throws CustomerNotFoundException;

}
