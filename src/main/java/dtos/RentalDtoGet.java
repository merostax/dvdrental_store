package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.sql.Timestamp;
import java.util.Map;

@JsonbPropertyOrder({"rentalId", "rentalDate", "returnDate", "customer", "store", "film"})
public class RentalDtoGet {
    private int rentalId;
    private Timestamp rentalDate;
    private Timestamp returnDate;
    private Map<String,String> customer;
    private Map<String,String> store;
    private Map<String,String> film;

    public int getRentalId() {
        return this.rentalId;
    }

    public void setRentalId(final int rentalId) {
        this.rentalId = rentalId;
    }

    public Timestamp getRentalDate() {
        return this.rentalDate;
    }

    public void setRentalDate(final Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Timestamp getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(final Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Map<String, String> getCustomer() {
        return this.customer;
    }

    public void setCustomer(final Map<String, String> customer) {
        this.customer = customer;
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
