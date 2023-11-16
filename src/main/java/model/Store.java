package model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private int storeId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff staffByManagerStaffId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address addressByAddressId;

    public int getStoreId() {
        return this.storeId;
    }

    public void setStoreId(final int storeId) {
        this.storeId = storeId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Staff getStaffByManagerStaffId() {
        return this.staffByManagerStaffId;
    }

    public void setStaffByManagerStaffId(final Staff staffByManagerStaffId) {
        this.staffByManagerStaffId = staffByManagerStaffId;
    }

    public Address getAddressByAddressId() {
        return this.addressByAddressId;
    }

    public void setAddressByAddressId(final Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
