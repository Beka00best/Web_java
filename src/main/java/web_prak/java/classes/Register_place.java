package web_prak.java.classes;

import lombok.*;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "register_place")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Register_place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "place_id")
    private long place_id;

    @Column(nullable = false, name = "storage_location")
    @NonNull
    private String storage_location;

    @Column(nullable = false, name = "free")
    @NonNull
    private String free;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register_place register_place = (Register_place) o;
        return Objects.equals(place_id, register_place.place_id)
                && storage_location.equals(register_place.storage_location)
                && free.equals(register_place.free);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place_id, storage_location, free, place_id);
    }
}