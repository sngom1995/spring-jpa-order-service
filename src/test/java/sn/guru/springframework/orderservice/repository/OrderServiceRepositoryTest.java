package sn.guru.springframework.orderservice.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import sn.guru.springframework.orderservice.domain.*;
import sn.guru.springframework.orderservice.repository.OrderServiceRepository;
import sn.guru.springframework.orderservice.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@ComponentScan(basePackages = "sn.guru.springframework.orderservice.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceRepositoryTest {

    @Autowired
    OrderServiceRepository orderServiceRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    Product product ;

    Customer customer;

    @BeforeEach
    void setUp() {
        Customer newCustomer = new Customer();
        newCustomer.setCustomer_name("New Customer");
        Product newProduct = new Product();
        newProduct.setStatus(ProductStatus.NEW);
        newProduct.setDescription("New Product");
        customer = customerRepository.saveAndFlush(newCustomer);
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testDeleteOrder() throws Exception {
        OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine = new OrderLine();
        OrderApproval approval = new OrderApproval();
        approval.setApprovedBy("me");
        orderLine.setQuantityOrdered(10);
        orderLine.setProduct(product);
        orderHeader.setCustomer(customer);
        orderHeader.setOrderApproval(approval);
        orderHeader.addOrderLine(orderLine);
        OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader);
        orderServiceRepository.flush();
        orderServiceRepository.delete(savedOrderHeader);
        orderServiceRepository.flush();
        assertThrows(NoSuchElementException.class,() -> {
            Optional<OrderHeader> orderHeaderOptional = orderServiceRepository.findById(savedOrderHeader.getId());

            assertNull(orderHeaderOptional.get());
        });
    }

    @Test
    void testSaveOrderHeaderWithOrderLine() {
        OrderHeader orderHeader = new OrderHeader();
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
        OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader);
        System.out.println(savedOrderHeader.getCreatedAt());
        assertThat(savedOrderHeader.getCreatedAt()).isEqualTo(orderHeader.getCreatedAt());
    }


    @Test
    void testUpdateOrderHeader() {
        Optional<OrderHeader> orderHeader = orderServiceRepository.findById(1L);
       if (orderHeader.isPresent()){
           OrderHeader orderHeader1 = orderHeader.get();
           OrderHeader savedOrderHeader = orderServiceRepository.save(orderHeader1);
           System.out.println(savedOrderHeader.getLastModifiedDate());
       }
    }
}
