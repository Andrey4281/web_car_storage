package ru.job4j.web_cars_storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="adverts")
public final class Advert {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @JsonProperty("created")
    @Column(name="created")
    private Timestamp created;
    @JsonProperty("photo")
    @Column(name="photo")
    private String photo;
    @JsonProperty("description")
    @Column(name="description")
    private String description;
    @Column(name="status")
    private boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user", foreignKey = @ForeignKey(name="adverts_users_id_fk"), nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_car", foreignKey = @ForeignKey(name="adverts_cars_id_fk"), nullable = false)
    private Car car;

    public Advert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return id == advert.id &&
                Objects.equals(created, advert.created) &&
                Objects.equals(photo, advert.photo) &&
                Objects.equals(description, advert.description) &&
                Objects.equals(user, advert.user) &&
                Objects.equals(car, advert.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, photo, description, user, car);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
