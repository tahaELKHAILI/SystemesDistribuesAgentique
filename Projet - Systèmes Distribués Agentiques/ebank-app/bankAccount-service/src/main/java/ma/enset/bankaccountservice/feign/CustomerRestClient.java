package ma.enset.bankaccountservice.feign;

import ma.enset.bankaccountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    Customer getCustomer(@PathVariable String id);

    @GetMapping("/customers")
    List<Customer> getCustomers();
}
