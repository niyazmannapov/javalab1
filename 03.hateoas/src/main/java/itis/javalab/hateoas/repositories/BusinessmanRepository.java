package itis.javalab.hateoas.repositories;

import itis.javalab.hateoas.models.Businessman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface BusinessmanRepository extends JpaRepository<Businessman, Long> {

}
