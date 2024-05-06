package com.example.web3;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration()
                    .addAnnotatedClass(Point.class)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
                    .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/studs")
                    .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .setProperty("hibernate.connection.username", "s367452")
                    .setProperty("hibernate.connection.password", "CSui7vGaIue5t9n6")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.format_sql", "true")
                    .setProperty("hbm2ddl.auto", "update");
            sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session createSession() {
        return sessionFactory.openSession();
    }
}