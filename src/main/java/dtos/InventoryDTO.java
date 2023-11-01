package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "store", "film"})
public class InventoryDTO {
    private int id;
    private String store;
    private String film;
}
