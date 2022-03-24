package web_prak.java.classes;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deliveries_list")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Deliveries_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "deliveries_list_id")
    private long deliveries_list_id;

    @ManyToOne(targetEntity = Products.class)
    @JoinColumn(name = "product_id")
    private Products product_id;

    @ManyToOne(targetEntity = Deliveries.class)
    @JoinColumn(name = "delivery_id")
    private Deliveries delivery_id;

    @Column(nullable = false, name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliveries_list deliveries_list = (Deliveries_list) o;
        return Objects.equals(deliveries_list_id, deliveries_list.deliveries_list_id)
                && product_id.equals(deliveries_list.product_id)
                && delivery_id.equals(deliveries_list.delivery_id)
                && Objects.equals(quantity, deliveries_list.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveries_list_id, product_id, delivery_id, quantity);
    }
}
