package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Car;

public interface DaoCar {
    Car findCar(Car car);
    void add(Car car);
}
