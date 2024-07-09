package med.voll.api.domain.direction;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direction {

    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;

    public Direction(DataDirection direction) {
        this.street = direction.street();
        this.number = direction.number();
        this.complement = direction.complement();
        this.district = direction.district();
        this.city = direction.city();
    }

    public Direction updateData(DataDirection direction) {

        this.street = direction.street();
        this.number = direction.number();
        this.complement = direction.complement();
        this.district = direction.district();
        this.city = direction.city();
        return this;
    }
}
