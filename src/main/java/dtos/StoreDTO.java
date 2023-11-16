package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id"})
public class StoreDTO {
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
