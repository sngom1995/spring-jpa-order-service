package sn.guru.springframework.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.guru.springframework.orderservice.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
