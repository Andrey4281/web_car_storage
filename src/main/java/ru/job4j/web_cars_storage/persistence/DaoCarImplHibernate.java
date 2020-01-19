package ru.job4j.web_cars_storage.persistence;

import ru.job4j.web_cars_storage.models.Car;

import java.util.List;

public class DaoCarImplHibernate implements DaoCar {
    private final static DaoCar INSTANCE = new DaoCarImplHibernate();

    private DaoCarImplHibernate() {

    }

    public static DaoCar getInstance() {
        return INSTANCE;
    }

    @Override
    public Car findCar(Car car) {
        return (Car) HibernateService.getInstance()
                .executeQuery(session -> {
                    Car res = null;
                    List<Car> list = session.createQuery("from Car where category=:paramOne and brand=:paramTwo and engine=:paramThree and transmission=:paramFour and carcass=:paramFive")
                            .setParameter("paramOne", car.getCategory())
                            .setParameter("paramTwo", car.getBrand())
                            .setParameter("paramThree", car.getEngine())
                            .setParameter("paramFour", car.getTransmission())
                            .setParameter("paramFive", car.getCarcass())
                            .list();
                    if (!list.isEmpty()) {
                        res = list.get(0);
                    }
                    return res;
                });
    }

    @Override
    public void add(Car car) {
        HibernateService.getInstance().executeUpdate(session -> session.save(car));
    }
}
