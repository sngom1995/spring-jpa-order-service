package sn.guru.springframework.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.guru.springframework.orderservice.domain.OrderHeader;


@Repository
public interface OrderServiceRepository extends JpaRepository<OrderHeader, Long> {
}
