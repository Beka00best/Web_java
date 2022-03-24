package web_prak.java.classes;

import lombok.*;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supplier_id")
    private long supplier_id;

    @Column(nullable = false, name = "supplier_name")
    @NonNull
    private String supplier_name;

    @Column(nullable = false, name = "contact")
    @NonNull
    private String contact;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suppliers suppliers = (Suppliers) o;
        return Objects.equals(supplier_id, suppliers.supplier_id)
                && supplier_name.equals(suppliers.supplier_name)
                && contact.equals(suppliers.contact)
                && address.equals(suppliers.address)
                && email.equals(suppliers.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier_id, supplier_name, contact, address, email);
    }
}
