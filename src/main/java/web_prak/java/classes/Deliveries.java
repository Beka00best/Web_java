package web_prak.java.classes;

import lombok.*;

import java.sql.Date;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Deliveries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "delivery_id")
    private long delivery_id;

    @ManyToOne(targetEntity = Clients.class)
    @JoinColumn(name = "client_id")
    private Clients client_id;

    @Column(nullable = false, name = "data_issue")
    @NonNull
    private Date data_issue;

    @Column(nullable = false, name = "status")
    @NonNull
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliveries deliveries = (Deliveries) o;
        return Objects.equals(delivery_id, deliveries.delivery_id)
                && client_id.equals(deliveries.client_id)
                && data_issue.equals(deliveries.data_issue)
                && status.equals(deliveries.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delivery_id, client_id, data_issue, status);
    }
}
