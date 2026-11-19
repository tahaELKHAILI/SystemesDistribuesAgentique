package ma.enset.customerservice;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.service.CustomerServiceInterface;
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
    CommandLineRunner start (CustomerServiceInterface customerServiceInterface){
        return args -> {
            Customer customer1 = Customer.builder()
                    .name("Jon")
                    .email("jon@mail.com")
                    .build();

            customerServiceInterface.addCustomer(customer1);

            Customer customer2 = Customer.builder()
                    .name("Jane")
                    .email("jane@mail.com")
                    .build();

            customerServiceInterface.addCustomer(customer2);
        };
    }
}
