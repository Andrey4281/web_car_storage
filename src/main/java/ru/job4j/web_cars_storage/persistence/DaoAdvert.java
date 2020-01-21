package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.User;
import ru.job4j.web_cars_storage.service.ContentForCriteria;
import ru.job4j.web_cars_storage.service.CriteriaForFilter;

import java.util.List;

public interface DaoAdvert {
    int add(Advert advert);
    void updateAdvert(Advert advert);
    void deleteAdvert(int id);
    Advert findAndvertById(int id);
    List<Advert> getAdvertsForCurrentUser(User user);
    List<Advert> findAdvertsByCriteria(CriteriaForFilter criteria, ContentForCriteria content);
}
