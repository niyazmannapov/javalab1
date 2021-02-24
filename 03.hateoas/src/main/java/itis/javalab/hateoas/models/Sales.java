package itis.javalab.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "sales_sale",
            joinColumns = @JoinColumn(name = "sales_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sales_id", referencedColumnName = "id")
    )
    private List<Sale> sales;

}
