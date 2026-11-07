package ma.enset.bankaccountservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.enset.bankaccountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerServiceFetchCustomer", fallbackMethod = "getDefaultCustomer")
    Customer getCustomer(@PathVariable String id);

    default Customer getDefaultCustomer(Exception ex, String id){
        return new Customer(id, "NoData", "NoData");
    }

    @GetMapping("/customers")
    List<Customer> getCustomers();
}
