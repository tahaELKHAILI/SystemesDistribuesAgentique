package ma.enset.customerservice.mcp;

import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.dtos.CustomerRequestDto;
import ma.enset.customerservice.exceptions.CustomerNotFoundException;
import ma.enset.customerservice.service.CustomerService;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMcpController {

    @Autowired
    CustomerService customerService;

    @McpTool(description = "Create / Add new customer")
    public Customer addCustomer(
            @McpArg(description = "This is the name of the customer") String name,
            @McpArg(description = "This is the email of the customer") String email
    ) {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(name, email);
        return customerService.addCustomer(customerRequestDto);
    }

    @McpTool(description = "Update an existing accounts details")
    public Customer updateCustomer(@McpArg(description = "This is the id of the customer to be updated") String id,
                                   @McpArg(description = "This is the updated name of the customer") String name,
                                   @McpArg(description = "This is the updated email of the customer") String email
    ) throws CustomerNotFoundException {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(name, email);
        return customerService.updateCustomer(id, customerRequestDto);
    }

    @McpTool(description = "Delete an existing customer")
    public void deleteCustomer(@McpArg(description = "The id of the customer to be deleted") String id) {
        customerService.deleteCustomer(id);
    }

    @McpTool(description = "Return a list of all the customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @McpTool(description = "Return the customer associated with the provided id")
    public Customer getCustomer(@McpArg(description = "The id of the customer") String id)
            throws CustomerNotFoundException {
        return customerService.getCustomer(id);
    }
}
