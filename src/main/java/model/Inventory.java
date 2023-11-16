package model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Inventory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id", nullable = false)
    private int inventoryId;
    @Basic
    @Column(name = "film_id", nullable = false)
    private int filmId;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private Store storeByStoreId;

    public int getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(final int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(final int filmId) {
        this.filmId = filmId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Store getStoreByStoreId() {
        return this.storeByStoreId;
    }

    public void setStoreByStoreId(final Store storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }
}
