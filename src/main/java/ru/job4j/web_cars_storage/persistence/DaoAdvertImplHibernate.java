package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.User;

import java.util.List;

public class DaoAdvertImplHibernate implements DaoAdvert {
    private final static DaoAdvert INSTANCE = new DaoAdvertImplHibernate();

    private DaoAdvertImplHibernate() {

    }

    public static DaoAdvert getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Advert advert) {
        HibernateService.getInstance().executeUpdate(session -> session.save(advert));
    }

    @Override
    public void updateAdvert(Advert advert) {
        HibernateService.getInstance().executeUpdate(session -> session.saveOrUpdate(advert));
    }

    @Override
    public void deleteAdvert(int id) {
        HibernateService.getInstance().executeUpdate(session -> session.createQuery("delete Advert where id=:param")
        .setParameter("param", id).executeUpdate());
            }

    @Override
    public Advert findAndvertById(int id) {
        return HibernateService.getInstance().executeQuery(session -> session.get(Advert.class, id));
    }

    @Override
    public List<Advert> getAllAdverts() {
        return HibernateService.getInstance().executeQuery(session -> session.createQuery("from Advert ").list());
    }

    @Override
    public List<Advert> getAdvertsForCurrentUser(User user) {
        return HibernateService.getInstance().executeQuery(session -> session.createQuery("from Advert where user=:param")
        .setParameter("param", user).list());
    }
}
