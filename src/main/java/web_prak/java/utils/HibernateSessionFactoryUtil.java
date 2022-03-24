package web_prak.java.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import web_prak.java.classes.*;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Clients.class);
                configuration.addAnnotatedClass(Deliveries.class);
                configuration.addAnnotatedClass(Deliveries_list.class);
                configuration.addAnnotatedClass(Products.class);
                configuration.addAnnotatedClass(Register_place.class);
                configuration.addAnnotatedClass(Suppliers.class);
                configuration.addAnnotatedClass(Supplies.class);
                configuration.addAnnotatedClass(Supplies_list.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
