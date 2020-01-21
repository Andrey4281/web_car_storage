package ru.job4j.web_cars_storage.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.Car;
import ru.job4j.web_cars_storage.models.Role;
import ru.job4j.web_cars_storage.models.User;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DaoAdvertTest {

    @BeforeEach
    void init() {
        Role role = new Role(1, "admin");
        HibernateService.getInstance().executeUpdate(session -> session.save(role));

    }

    @Test
    void whenAddAdvertShouldGetIt() {
        Advert actual = new Advert();
        actual.setCreated(new Timestamp(System.currentTimeMillis()));
        actual.setDescription("advert");
        Car car = new Car("category2", "brand2", "engine2", "transmission2", "carcass2");
        User user = new User("login1", "phone1", "password1");
        user.setRole(new Role(1, "admin"));
        actual.setCar(car);
        actual.setUser(user);
        HibernateService.getInstance().executeUpdate(session -> session.save(car));
        HibernateService.getInstance().executeUpdate(session -> session.save(user));
        DaoAdvert dao = DaoAdvertImplHibernate.getInstance();

        Integer id = dao.add(actual);
        Advert expected = dao.findAndvertById(id);

        assertEquals(expected, actual);
    }

    @Test
    void whenUpdateAdvertShouldGetUpdatedValue() {
        Advert actual = new Advert();
        actual.setCreated(new Timestamp(System.currentTimeMillis()));
        actual.setDescription("advert");
        Car car = new Car("category3", "brand3", "engine3", "transmission3", "carcass3");
        User user = new User("login2", "phone2", "password2");
        user.setRole(new Role(1, "admin"));
        actual.setCar(car);
        actual.setUser(user);
        HibernateService.getInstance().executeUpdate(session -> session.save(car));
        HibernateService.getInstance().executeUpdate(session -> session.save(user));
        DaoAdvert dao = DaoAdvertImplHibernate.getInstance();

        Integer id = dao.add(actual);
        actual.setStatus(true);
        actual.setDescription("new Desc");
        dao.updateAdvert(actual);
        Advert expected = dao.findAndvertById(id);

        assertEquals(expected, actual);
    }

    @Test
    void whenDeleteAdvertShouldNotGetIt() {
        Advert actual = new Advert();
        actual.setCreated(new Timestamp(System.currentTimeMillis()));
        actual.setDescription("advert");
        Car car = new Car("category4", "brand4", "engine4", "transmission4", "carcass4");
        User user = new User("login3", "phone3", "password3");
        user.setRole(new Role(1, "admin"));
        actual.setCar(car);
        actual.setUser(user);
        HibernateService.getInstance().executeUpdate(session -> session.save(car));
        HibernateService.getInstance().executeUpdate(session -> session.save(user));
        DaoAdvert dao = DaoAdvertImplHibernate.getInstance();

        Integer id = dao.add(actual);
        dao.deleteAdvert(id);
        Advert expected = dao.findAndvertById(id);

        assertNull(expected);
    }

}
