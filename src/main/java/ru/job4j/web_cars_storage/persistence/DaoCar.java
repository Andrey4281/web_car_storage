package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Car;

public interface DaoCar {
    Car findCarByParameters(String category, String brand, String engine, String transmission,
                            String carcass);
    void add(Car car);
}
