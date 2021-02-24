package itis.javalab.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Businessman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isActive;

    private String country;
    private String city;

    @ManyToMany(mappedBy = "businessman")
    private Set<Sale> sales;

    @ManyToMany(mappedBy = "album_businessman")
    private Set<Business> businesses;

    @ManyToOne
    private Company company;

    public void deactivate() {
        if (isActive) {
            isActive = false;
        } else {
            throw new IllegalStateException();
        }
    }
}
