package sn.guru.springframework.orderservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderHeaderTest {

    @Test
    void testEquals() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setId(1L);
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(1L);
        assertEquals(orderHeader, orderHeader1);
    }
    @Test
    void testNotEquals() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setId(1L);
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(2L);
        assertNotEquals(orderHeader, orderHeader1);
    }
}
