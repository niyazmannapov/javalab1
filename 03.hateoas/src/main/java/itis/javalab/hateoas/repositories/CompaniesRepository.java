package itis.javalab.hateoas.repositories;

import itis.javalab.hateoas.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Company, Long> {
}
