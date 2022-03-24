package web_prak.java.classes;

import lombok.*;

import java.sql.Date;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supplies")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Supplies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supply_id")
    private long supply_id;

    @ManyToOne(targetEntity = Suppliers.class)
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier_id;

    @Column(nullable = false, name = "data_supply")
    @NonNull
    private Date data_supply;

    @Column(nullable = false, name = "status")
    @NonNull
    private String status;

    @Column(nullable = false, name = "store_period")
    private int store_period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplies supplies = (Supplies) o;
        return Objects.equals(supply_id, supplies.supply_id)
                && supplier_id.equals(supplies.supplier_id)
                && data_supply.equals(supplies.data_supply)
                && status.equals(supplies.status)
                && Objects.equals(store_period, supplies.store_period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supply_id, supplier_id, data_supply, status, store_period);
    }
}
