package ru.job4j.web_cars_storage.service;

import ru.job4j.web_cars_storage.models.Car;

@FunctionalInterface
public interface AddCarInterface {
    void addField(Car car, String param);
}
