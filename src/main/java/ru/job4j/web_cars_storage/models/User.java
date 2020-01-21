package ru.job4j.web_cars_storage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users")
public final class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @JsonProperty("login")
    @Column(name = "login", nullable = false)
    private String login;
    @JsonProperty("phone")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @JsonIgnore
    @Column(name = "password", nullable = false, unique = true)
    private String password;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_role", foreignKey = @ForeignKey(name = "users_roles_id_fk"), nullable = false)
    private Role role;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Advert> adverts;

    public User() {
    }

    public User(String login, String phone, String password) {
        this.login = login;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, phone, password, role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
