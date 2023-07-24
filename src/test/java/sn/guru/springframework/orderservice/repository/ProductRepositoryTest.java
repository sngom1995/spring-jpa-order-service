package sn.guru.springframework.orderservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import sn.guru.springframework.orderservice.domain.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ComponentScan(basePackages = "sn.guru.springframework.orderservice.repository" )
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testGetProductByDescription() {
        Product product = productRepository.findProductByDescription("PRODUCT1");

        assertThat(product.getCategories().size()).isGreaterThan(0);
        assertThat(product).isNotNull();
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("Test description");
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getCreatedAt()).isBefore(Timestamp.valueOf(LocalDateTime.now()));
    }
}
