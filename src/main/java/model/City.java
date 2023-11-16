package model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id", nullable = false)
    private int cityId;
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    private Country countryByCountryId;

    public int getCityId() {
        return this.cityId;
    }

    public void setCityId(final int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Country getCountryByCountryId() {
        return this.countryByCountryId;
    }

    public void setCountryByCountryId(final Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }
}
