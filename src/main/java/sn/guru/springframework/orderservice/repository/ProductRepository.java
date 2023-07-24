package sn.guru.springframework.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.guru.springframework.orderservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByDescription(String description);
}
