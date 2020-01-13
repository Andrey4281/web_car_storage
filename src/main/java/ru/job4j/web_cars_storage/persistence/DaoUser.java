package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.User;

import java.util.List;

public interface DaoUser {
    void add(User user);
    User findUserByLogin(String login);
    List<User> findUsersByLoginOrPhone(String login, String phone);
}
