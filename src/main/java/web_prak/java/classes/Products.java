package web_prak.java.classes;

import lombok.*;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "product_id")
    private long product_id;

    @Column(nullable = false, name = "product_name")
    @NonNull
    private String product_name;

    @Column(nullable = false, name = "type")
    @NonNull
    private String type;

    @Column(nullable = false, name = "quantity")
    private int quantity;

    @Column(nullable = false, name = "measure")
    @NonNull
    private String measure;

    @Column(nullable = false, name = "expiration_date_from")
    @NonNull
    private Date expiration_date_from;

    @Column(nullable = false, name = "expiration_date_to")
    @NonNull
    private Date expiration_date_to;

    @ManyToOne(targetEntity = Register_place.class)
    @JoinColumn(name = "place_id")
    private Register_place place_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(product_id, products.product_id)
                && product_name.equals(products.product_name)
                && type.equals(products.type)
                && Objects.equals(quantity, products.quantity)
                && measure.equals(products.measure)
                && expiration_date_from.equals(products.expiration_date_from)
                && expiration_date_to.equals(products.expiration_date_to)
                && place_id.equals(products.place_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id,
                product_name, type, quantity, measure, expiration_date_from, expiration_date_to,
                place_id);
    }
}
