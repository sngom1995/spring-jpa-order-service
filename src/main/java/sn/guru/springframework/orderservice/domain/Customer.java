package sn.guru.springframework.orderservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@AttributeOverrides(
        {
                @AttributeOverride(name="contact.email",
                        column = @Column(name = "email")
                )
                ,
                @AttributeOverride(name="contact.phone",
                        column = @Column(name = "phone")
                )
        }
)
@Entity
public class Customer extends BaseEntity{
   private String customer_name;

    @Embedded
    private ContactInfo contact;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders;


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public void setOrderHeaders(Set<OrderHeader> orderHeaders) {
        this.orderHeaders = orderHeaders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(customer_name, customer.customer_name))
            return false;
        return Objects.equals(contact, customer.contact);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customer_name != null ? customer_name.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }
}
