package sn.guru.springframework.orderservice.domain;


import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@AttributeOverrides(
        {
                @AttributeOverride(name="shippingAddress.address",
                        column = @Column(name = "shipping_address")
                )
                ,
                @AttributeOverride(name="shippingAddress.city",
                        column = @Column(name = "shipping_city")
                )
                ,
                @AttributeOverride(name="shippingAddress.state",
                        column = @Column(name = "shipping_state")
                )
                ,
                @AttributeOverride(name="shippingAddress.zipCode",
                        column = @Column(name = "shipping_zip_code")
                )
                ,
                @AttributeOverride(
                        name="billingAddress.address",
                        column = @Column(name = "billing_address")
                )
                ,
                @AttributeOverride(name="billingAddress.city",
                        column = @Column(name = "bill_to_city")
                )
                ,
                @AttributeOverride(name="billingAddress.state",
                        column = @Column(name = "bill_to_state")
                )
                ,
                @AttributeOverride(name="billingAddress.zipCode",
                        column = @Column(name = "bill_to_zip_code")
                )
        }
)
@Entity
public class OrderHeader extends BaseEntity{

    private String customerName;


    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @OneToMany(mappedBy = "orderHeader",cascade = CascadeType.PERSIST)
    private Set<OrderLine> orderLines;
    public OrderStatus getStatus() {
        return status;
    }

    public void addOrderLine(OrderLine orderLine) {
       if (orderLines == null) {
           orderLines = new HashSet<>();
       }
       orderLines.add(orderLine);
       orderLine.setOrderHeader(this);
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderHeader() {

    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderHeader(String customerName) {
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

        if (!Objects.equals(customerName, that.customerName)) return false;
        if (!Objects.equals(shippingAddress, that.shippingAddress))
            return false;
        if (!Objects.equals(billingAddress, that.billingAddress))
            return false;
        if (status != that.status) return false;
        return Objects.equals(orderLines, that.orderLines);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "customerName='" + customerName + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", status=" + status +
                ", orderLines=" + orderLines +
                '}';
    }
}
