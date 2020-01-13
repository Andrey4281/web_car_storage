package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.User;

import java.util.List;

public final class DaoUserImplHibernate implements DaoUser {
    private final static DaoUser INSTANCE = new DaoUserImplHibernate();

    private DaoUserImplHibernate() {

    }

    public static DaoUser getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        HibernateService.getInstance().executeUpdate(session -> session.save(user));
    }

    @Override
    public User findUserByLogin(String login) {
        return HibernateService.getInstance()
                .executeQuery(session -> {
                    User res = null;
                    List<User> list =  session.createQuery("from User where login=:param")
                            .setParameter("param", login).list();
                    if (!list.isEmpty()) {
                        res = list.get(0);
                    }
                    return res;
                });
    }

    @Override
    public List<User> findUsersByLoginOrPhone(String login, String phone) {
        return HibernateService.getInstance()
                .executeQuery(session -> {
                    return session.createQuery("from User where login=:paramOne or phone=:paramTwo")
                            .setParameter("paramOne", login)
                            .setParameter("paramTwo", phone)
                            .list();
                });
    }
}
