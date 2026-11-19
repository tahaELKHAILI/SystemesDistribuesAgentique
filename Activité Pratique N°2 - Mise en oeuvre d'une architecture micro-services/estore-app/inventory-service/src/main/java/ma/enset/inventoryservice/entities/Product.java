package ma.enset.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}
