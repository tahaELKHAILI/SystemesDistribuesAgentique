package ma.enset.inventoryservice.service;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductServiceInterface {

    public List<Product> getProducts();
    public Product getProduct(Long id) throws ProductNotFoundException;
    public Product addProduct(Product product);
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    public void deleteProduct(Long id);
}
