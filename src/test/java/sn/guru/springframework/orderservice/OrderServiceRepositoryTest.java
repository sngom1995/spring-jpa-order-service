package sn.guru.springframework.orderservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import sn.guru.springframework.orderservice.domain.OrderHeader;
import sn.guru.springframework.orderservice.domain.OrderLine;
import sn.guru.springframework.orderservice.domain.Product;
import sn.guru.springframework.orderservice.domain.ProductStatus;
import sn.guru.springframework.orderservice.repository.OrderServiceRepository;
import sn.guru.springframework.orderservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ComponentScan(basePackages = "sn.guru.springframework.orderservice.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceRepositoryTest {

    @Autowired
    OrderServiceRepository orderServiceRepository;

    @Autowired
    ProductRepository productRepository;

    Product product ;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setStatus(ProductStatus.NEW);
        newProduct.setDescription("New Product");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrderHeaderWithOrderLine() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("Ibrahime Ndao");
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(10);
        orderLine.setProduct(product);
        orderHeader.addOrderLine(orderLine);
        OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader);
        orderServiceRepository.flush();

        assertThat(savedOrderHeader.getOrderLines().size()).isEqualTo(1);
    }

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
