package model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_id", nullable = false)
    private int staffId;
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Basic
    @Column(name = "store_id", nullable = false)
    private int storeId;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @Basic
    @Column(name = "username", nullable = false, length = 16)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = 40)
    private String password;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Basic
    @Column(name = "picture", nullable = true)
    private byte[] picture;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address addressByAddressId;

    public int getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public void setStoreId(final int storeId) {
        this.storeId = storeId;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(final byte[] picture) {
        this.picture = picture;
    }

    public Address getAddressByAddressId() {
        return this.addressByAddressId;
    }

    public void setAddressByAddressId(final Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
