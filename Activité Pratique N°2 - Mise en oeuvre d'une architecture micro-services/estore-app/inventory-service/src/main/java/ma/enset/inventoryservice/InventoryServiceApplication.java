package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.service.ProductServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductServiceInterface productServiceInterface){
        return args -> {
            productServiceInterface.addProduct(new Product(null,"product1",200.0,200));
            productServiceInterface.addProduct(new Product(null,"product2",10000.0,1));
            productServiceInterface.addProduct(new Product(null,"product3",2000.0,2));
            productServiceInterface.addProduct(new Product(null,"product4",220.0,100));
            productServiceInterface.addProduct(new Product(null,"product5",20.0,200));

        };
    }
}
