package web_prak.java.classes;

import lombok.*;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "client_id")
    private long client_id;

    @Column(nullable = false, name = "client_name")
    @NonNull
    private String client_name;

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
        Clients clients = (Clients) o;
        return Objects.equals(client_id, clients.client_id)
                && client_name.equals(clients.client_name)
                && contact.equals(clients.contact)
                && address.equals(clients.address)
                && email.equals(clients.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, client_name, contact, address, email);
    }
}
