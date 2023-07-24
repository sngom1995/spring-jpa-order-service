package sn.guru.springframework.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import sn.guru.springframework.orderservice.domain.OrderHeader;
import sn.guru.springframework.orderservice.repository.OrderServiceRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ComponentScan(basePackages = "sn.guru.springframework.orderservice.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceRepositoryTest {

    @Autowired
    OrderServiceRepository orderServiceRepository;

    @Test
    void testGetsAllOrders(){
        List<OrderHeader> orders = orderServiceRepository.findAll();
        assertThat(orders.size()).isGreaterThan(0);
    }

    @Test
    void testSaveOrderHeader() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("Ibrahime Ndao");
        OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader);
        System.out.println(savedOrderHeader.getCreatedAt());
        assertThat(savedOrderHeader.getCreatedAt()).isEqualTo(orderHeader.getCreatedAt());
    }


    @Test
    void testUpdateOrderHeader() {
        Optional<OrderHeader> orderHeader = orderServiceRepository.findById(1L);
       if (orderHeader.isPresent()){
           OrderHeader orderHeader1 = orderHeader.get();
           orderHeader1.setCustomerName("Ibrahime Ndao");
           OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader1);
           System.out.println(savedOrderHeader.getLastModifiedDate());
           assertThat(savedOrderHeader.getCustomerName()).isEqualTo(orderHeader1.getCustomerName());
       }
    }
}
