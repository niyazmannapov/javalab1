package itis.javalab.hateoas.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer length;

    @ManyToMany
    @JoinTable(
            name = "sale_businessman",
            joinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "businessman_id", referencedColumnName = "id")
    )
    private List<Businessman> businessmen;

    @ManyToOne()
    private Business business;

    @ManyToMany(mappedBy = "sales")
    private List<Sales> sales;

}
