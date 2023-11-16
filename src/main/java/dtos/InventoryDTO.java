package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.Map;

@JsonbPropertyOrder({"id", "store", "film"})
public class InventoryDTO {
    private int id;
    private Map<String,String> store;
    private Map<String,String> film;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Map<String, String> getStore() {
        return this.store;
    }

    public void setStore(final Map<String, String> store) {
        this.store = store;
    }

    public Map<String, String> getFilm() {
        return this.film;
    }

    public void setFilm(final Map<String, String> film) {
        this.film = film;
    }
}
