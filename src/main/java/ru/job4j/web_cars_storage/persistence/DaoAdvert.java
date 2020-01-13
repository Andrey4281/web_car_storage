package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.User;

import java.util.List;

public interface DaoAdvert {
    void add(Advert advert);
    void updateAdvert(Advert advert);
    void deleteAdvert(int id);
    Advert findAndvertById(int id);
    List<Advert> getAllAdverts();
    List<Advert> getAdvertsForCurrentUser(User user);
}
