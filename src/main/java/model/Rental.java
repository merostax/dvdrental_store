package model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Rental {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id", nullable = false)
    private int rentalId;
    @Basic
    @Column(name = "rental_date", nullable = false)
    private Timestamp rentalDate ;

    @Basic
    @Column(name = "customer_id", nullable = false)
    private int customerId;
    @Basic
    @Column(name = "return_date", nullable = true)
    private Timestamp returnDate;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Timestamp(System.currentTimeMillis());
    }
    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", nullable = false)
    private Inventory inventoryByInventoryId;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff staffByStaffId;

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

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(final int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(final Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Inventory getInventoryByInventoryId() {
        return this.inventoryByInventoryId;
    }

    public void setInventoryByInventoryId(final Inventory inventoryByInventoryId) {
        this.inventoryByInventoryId = inventoryByInventoryId;
    }

    public Staff getStaffByStaffId() {
        return this.staffByStaffId;
    }

    public void setStaffByStaffId(final Staff staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }
}
