package ma.enset.customerservice;

import ma.enset.customerservice.domain.Customer;
import ma.enset.customerservice.dtos.CustomerRequestDto;
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
            CustomerRequestDto customerRequestDto = CustomerRequestDto.builder()
                    .name("user")
                    .email("user@gmail.com")
                    .build();

            customerService.addCustomer(customerRequestDto);

            CustomerRequestDto customerRequestDto2 = CustomerRequestDto.builder()
                    .name("user2")
                    .email("user2@gmail.com")
                    .build();

            customerService.addCustomer(customerRequestDto2);
        };
    }
}
