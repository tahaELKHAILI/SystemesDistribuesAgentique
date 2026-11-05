package ma.enset.customerservice;

import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }



    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            Customer customer = Customer.builder()
                    .name("user")
                    .email("user@gmail.com")
                    .build();

            customerService.addCustomer(customer);

            Customer customer1 = Customer.builder()
                    .name("user2")
                    .email("user2@gmail.com")
                    .build();

            customerService.addCustomer(customer1);
        };
    }
}
