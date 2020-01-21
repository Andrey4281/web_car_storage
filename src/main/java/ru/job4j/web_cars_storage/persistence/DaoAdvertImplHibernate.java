package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.User;
import ru.job4j.web_cars_storage.service.ContentForCriteria;
import ru.job4j.web_cars_storage.service.CriteriaForFilter;

import java.util.*;
import java.util.function.Function;

public class DaoAdvertImplHibernate implements DaoAdvert {
    private final static DaoAdvert INSTANCE = new DaoAdvertImplHibernate();
    private final DispatchPattern dispatchPattern = new DispatchPattern();
    private static class DispatchPattern {
        private final Map<CriteriaForFilter, Function<ContentForCriteria, List<Advert>>> dispatch = new HashMap<>();

        public void init() {
            CriteriaForFilter criteria = new CriteriaForFilter(true, false, false);

            dispatch.put(criteria, content -> {
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert where created >:parameter")
                        .setParameter("parameter", calendar.getTime())
                        .list());
            });

            criteria = new CriteriaForFilter(false, true, false);
            dispatch.put(criteria, content -> {
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert where photo not like '%empty%'")
                        .list());
            });

            criteria = new CriteriaForFilter(false, false, true);
            dispatch.put(criteria, content -> {
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                                .createQuery("from Advert where car.brand=:parameter")
                                .setParameter("parameter", content.getBrand()).list());
            });

            criteria = new CriteriaForFilter(true, true, false);
            dispatch.put(criteria, content -> {
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert where created >: parameter and photo not like '%empty%'")
                        .setParameter("parameter", calendar.getTime().getTime())
                        .list());
            });

            criteria = new CriteriaForFilter(false, true, true);
            dispatch.put(criteria, content -> {
               return HibernateService.getInstance()
               .executeQuery(session -> session
               .createQuery("from Advert where photo not like '%empty%' and car.brand =: parameter")
               .setParameter("parameter", content.getBrand()).list());
            });

            criteria = new CriteriaForFilter(true, false, true);
            dispatch.put(criteria, content -> {
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert where created >: parameterOne and car.brand =: parameterTwo")
                        .setParameter("parameterOne", calendar.getTime().getTime())
                        .setParameter("parameterTwo", content.getBrand()).list());
            });

            criteria = new CriteriaForFilter(true, true, true);
            dispatch.put(criteria, content -> {
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert where created >: parameterOne and photo not like '%empty%' and car.brand =: parameterTwo")
                        .setParameter("parameterOne", calendar.getTime())
                        .setParameter("parameterTwo", content.getBrand()).list());
            });

            criteria = new CriteriaForFilter(false, false, false);
            dispatch.put(criteria, content -> {
                return HibernateService.getInstance()
                        .executeQuery(session -> session
                        .createQuery("from Advert").list());
            });
        }
    }

    private DaoAdvertImplHibernate() {
        dispatchPattern.init();
    }

    public static DaoAdvert getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Advert advert) {
        return HibernateService.getInstance().executeQuery(session -> (Integer)session.save(advert));
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
    public List<Advert> getAdvertsForCurrentUser(User user) {
        return HibernateService.getInstance().executeQuery(session -> session.createQuery("from Advert where user=:param")
        .setParameter("param", user).list());
    }

    @Override
    public List<Advert> findAdvertsByCriteria(CriteriaForFilter criteria, ContentForCriteria content) {
        return dispatchPattern.dispatch.get(criteria).apply(content);
    }
}
