package sn.guru.springframework.orderservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class OrderHeader extends BaseEntity{

    private String customerName;

    public OrderHeader() {

    }

    public OrderHeader( String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "OrderHeader{" +
                "customerName='" + customerName + '\'' +
                '}';
    }
}
