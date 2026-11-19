package ma.enset.inventoryservice.service;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.exceptions.ProductNotFoundException;
import ma.enset.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductServiceInterface {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(String.format("There is no product with Id %d", id)));
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Product updateProduct = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException(String.format("There is no product with ID %d", id)));

        if(product.getName() != null) updateProduct.setName(product.getName());
        if(product.getPrice() != null) updateProduct.setPrice(product.getPrice());
        if(product.getQuantity() != null) updateProduct.setQuantity(product.getQuantity());

        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
