package ma.enset.customerservice.service;

import jakarta.transaction.Transactional;
import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;
import ma.enset.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
        Customer savedAccount = customerRepository.save(newCustomer);
        return savedAccount;
    }

    @Override
    public Customer updateCustomer(String id ,Customer customer) throws CustomerNotFoundException {
        Customer oldCustomer = customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException(String.format("Customer with id %s does not exist",id)));

        if(customer.getName()!=null) oldCustomer.setName(customer.getName());
        if(customer.getEmail()!=null) oldCustomer.setEmail(customer.getEmail());

        Customer updatedAccount = customerRepository.save(oldCustomer);
        return updatedAccount;
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException(String.format("Customer with id %s does not exist",id)));
        return customer;
    }
}
