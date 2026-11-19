package ma.enset.customerservice.service;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;
import ma.enset.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerServiceInterface{

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer getCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(String.format("There is no customer with id %d", id)));
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws CustomerNotFoundException {
        Customer customerModify = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(String.format("There is no customer with the id %d", id)));

        if(customer.getName() != null) customerModify.setName(customer.getName());
        if(customer.getEmail() != null) customerModify.setEmail(customer.getEmail());

        return customerRepository.save(customerModify);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
