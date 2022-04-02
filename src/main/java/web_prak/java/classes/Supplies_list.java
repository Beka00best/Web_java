package web_prak.java.classes;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supplies_list")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Supplies_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supplies_list_id")
    private long supplies_list_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products product_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supply_id")
    private Supplies supply_id;

    @Column(nullable = false, name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplies_list supplies_list = (Supplies_list) o;
        return Objects.equals(supplies_list_id, supplies_list.supplies_list_id)
                && product_id.equals(supplies_list.product_id)
                && supply_id.equals(supplies_list.supply_id)
                && Objects.equals(quantity, supplies_list.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplies_list_id, product_id, supply_id, quantity);
    }
}
