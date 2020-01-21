package ru.job4j.web_cars_storage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="cars")
public final class Car {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @JsonProperty("category")
    @Column(name = "category", nullable = false)
    private String category;
    @JsonProperty("brand")
    @Column(name = "brand", nullable = false)
    private String brand;
    @JsonProperty("engine")
    @Column(name = "engine", nullable = false)
    private String engine;
    @JsonProperty("transmission")
    @Column(name = "transmission", nullable = false)
    private String transmission;
    @JsonProperty("carcass")
    @Column(name = "carcass", nullable = false)
    private String carcass;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Advert> adverts;

    public Car() {
    }

    public Car(String category, String brand, String engine, String transmission, String carcass) {
        this.category = category;
        this.brand = brand;
        this.engine = engine;
        this.transmission = transmission;
        this.carcass = carcass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCarcass() {
        return carcass;
    }

    public void setCarcass(String carcass) {
        this.carcass = carcass;
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(category, car.category) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(carcass, car.carcass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, brand, engine, transmission, carcass);
    }
}
