package ru.job4j.web_cars_storage.logic;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.Car;
import ru.job4j.web_cars_storage.models.User;
import ru.job4j.web_cars_storage.service.ContentForCriteria;
import ru.job4j.web_cars_storage.service.CriteriaForFilter;

import java.util.List;

public interface Logic {
    void addUser(User user);
    void addCar(Car car);
    void addAdvert(Advert advert);
    User findUserByLogin(String login);
    List<User> findUsersByLoginOrPhone(String login, String phone);
    Car findCar(Car car);
    Advert findAdvertById(int id);
    List<Advert> getAdvertsForCurrentUser(User user);
    void updateAdvert(Advert advert);
    void deleteAdvert(int id);
    List<Advert> findAdvertsByCriteria(CriteriaForFilter criteria, ContentForCriteria content);
}
