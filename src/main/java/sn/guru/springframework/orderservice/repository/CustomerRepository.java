package sn.guru.springframework.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.guru.springframework.orderservice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
