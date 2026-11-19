package ma.enset.inventoryservice.web;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.exceptions.ProductNotFoundException;
import ma.enset.inventoryservice.service.ProductServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private ProductServiceInterface productServiceInterface;

    public ProductRestController(ProductServiceInterface productServiceInterface){
        this.productServiceInterface = productServiceInterface;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productServiceInterface.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productServiceInterface.getProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productServiceInterface.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product)
            throws ProductNotFoundException{
        return productServiceInterface.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productServiceInterface.deleteProduct(id);
    }

    @ExceptionHandler
    public String handler(Exception ex){
        return ex.getMessage();
    }
}
