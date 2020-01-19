package ru.job4j.web_cars_storage.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public final class HibernateService {
    private static final Logger LOG = LogManager.getLogger(HibernateService.class);
    private static final class Holder {
        private static final HibernateService INSTANCE = new HibernateService();
    }
    private final SessionFactory factory;

    private HibernateService() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static HibernateService getInstance() {
        return HibernateService.Holder.INSTANCE;
    }

    public <T> T executeQuery(final Function<Session, T> query) {
        final Session session = factory.openSession();
        try {
            T rsl = query.apply(session);
            return rsl;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
    }

    public void executeUpdate(final Consumer<Session> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
    }
}
