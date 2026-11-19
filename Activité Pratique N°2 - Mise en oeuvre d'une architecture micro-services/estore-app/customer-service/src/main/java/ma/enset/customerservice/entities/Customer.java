package ma.enset.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String email;
}
