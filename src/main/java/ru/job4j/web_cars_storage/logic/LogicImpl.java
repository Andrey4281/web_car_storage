package ru.job4j.web_cars_storage.logic;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.Car;
import ru.job4j.web_cars_storage.models.User;
import ru.job4j.web_cars_storage.persistence.*;

import java.util.List;

public class LogicImpl implements Logic {
    private final static Logic INSTANCE = new LogicImpl();
    private final DaoUser daoUser = DaoUserImplHibernate.getInstance();
    private final DaoCar daoCar = DaoCarImplHibernate.getInstance();
    private final DaoAdvert daoAdvert = DaoAdvertImplHibernate.getInstance();

    private LogicImpl() {

    }

    public static Logic getInstance() {
        return INSTANCE;
    }

    @Override
    public void addUser(User user) {
        daoUser.add(user);
    }

    @Override
    public void addCar(Car car) {
        daoCar.add(car);
    }

    @Override
    public void addAdvert(Advert advert) {
        daoAdvert.add(advert);
    }

    @Override
    public User findUserByLogin(String login) {
        return daoUser.findUserByLogin(login);
    }

    @Override
    public List<User> findUsersByLoginOrPhone(String login, String phone) {
        return daoUser.findUsersByLoginOrPhone(login, phone);
    }

    @Override
    public Car findCarByParameters(String category, String brand, String engine, String transmission, String carcass) {
        return daoCar.findCarByParameters(category, brand, engine, transmission, carcass);
    }

    @Override
    public Advert findAdvertById(int id) {
        return daoAdvert.findAndvertById(id);
    }

    @Override
    public List<Advert> getAllAdverts() {
        return daoAdvert.getAllAdverts();
    }

    @Override
    public List<Advert> getAdvertsForCurrentUser(User user) {
        return daoAdvert.getAdvertsForCurrentUser(user);
    }

    @Override
    public void updateAdvert(Advert advert) {
        daoAdvert.updateAdvert(advert);
    }

    @Override
    public void deleteAdvert(int id) {
        daoAdvert.deleteAdvert(id);
    }
}
