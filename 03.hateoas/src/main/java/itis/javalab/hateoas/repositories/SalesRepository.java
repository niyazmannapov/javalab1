package itis.javalab.hateoas.repositories;

import itis.javalab.hateoas.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
