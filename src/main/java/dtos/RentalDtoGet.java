package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"rentalId", "rentalDate", "returnDate", "customer", "store", "film"})
public class RentalDtoGet {
    private int rentalId;
    private Timestamp rentalDate;
    private Timestamp returnDate;
    private Map<String,String> customer;
    private Map<String,String> store;
    private Map<String,String> film;
}
