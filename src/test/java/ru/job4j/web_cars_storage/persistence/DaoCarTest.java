package ru.job4j.web_cars_storage.persistence;

import org.junit.jupiter.api.Test;
import ru.job4j.web_cars_storage.models.Car;

import static org.junit.jupiter.api.Assertions.*;

class DaoCarTest {

    @Test
    void whenAddCarShouldGetIt() {
        Car actual = new Car("category1", "brand1", "engine1", "transmission1", "carcass");
        DaoCar dao = DaoCarImplHibernate.getInstance();

        dao.add(actual);
        Car expected = dao.findCar(actual);

        assertEquals(expected, actual);
    }

}
