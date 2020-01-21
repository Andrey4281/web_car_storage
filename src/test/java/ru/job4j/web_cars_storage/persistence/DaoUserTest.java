package ru.job4j.web_cars_storage.persistence;

import org.junit.jupiter.api.Test;
import ru.job4j.web_cars_storage.models.Role;
import ru.job4j.web_cars_storage.models.User;

import static org.junit.jupiter.api.Assertions.*;

class DaoUserTest {

    @Test
    void whenAddUserShouldGetIt() {
        User actual = new User("login", "phone", "password");
        Role role = new Role(1, "admin");
        HibernateService.getInstance().executeUpdate(session -> session.save(role));
        actual.setRole(role);
        DaoUser dao = DaoUserImplHibernate.getInstance();

        dao.add(actual);
        User expected = dao.findUserByLogin("login");

        assertEquals(expected, actual);
    }
}
