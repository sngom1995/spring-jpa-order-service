package sn.guru.springframework.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.guru.springframework.orderservice.domain.OrderApproval;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
