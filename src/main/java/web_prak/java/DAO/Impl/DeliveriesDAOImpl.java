package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.DeliveriesDAO;
import web_prak.java.classes.Deliveries;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.List;

public class DeliveriesDAOImpl implements DeliveriesDAO {
    @Override
    public void createDeliveries(Deliveries deliveries) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(deliveries);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateDeliveries(Deliveries deliveries) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(deliveries);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteDeliveries(Deliveries deliveries) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(deliveries);
        tx1.commit();
        session.close();
    }

    @Override
    public Deliveries getDeliveriesById(long delivery_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Deliveries deliveries = session.get(Deliveries.class, delivery_id);
        session.close();
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesByClient(long client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries> query = session.createQuery("SELECT d FROM Deliveries d JOIN d.client_id s " +
                "WHERE s.client_id = :param", Deliveries.class).setParameter("param", client_id);
        List<Deliveries> deliveries = query.getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesDate(Date data_issue) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries> query = session.createQuery("FROM Deliveries WHERE date_issue = :param",
                        Deliveries.class).setParameter("param", data_issue);
        List<Deliveries> deliveries = query.getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesDateAfter(Date data_issue) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries> query = session.createQuery("FROM Deliveries WHERE date_issue <= :param",
                        Deliveries.class).setParameter("param", data_issue);
        List<Deliveries> deliveries = query.getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesDateBefore(Date data_issue) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries> query = session.createQuery("FROM Deliveries WHERE date_issue >= :param",
                        Deliveries.class).setParameter("param", data_issue);
        List<Deliveries> deliveries = query.getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesByStatus(String status) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries> query = session.createQuery("FROM Deliveries WHERE status = :param",
                        Deliveries.class).setParameter("param", status);
        List<Deliveries> deliveries = query.getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }

    @Override
    public List<Deliveries> getDeliveriesAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Deliveries> criteria = session.getCriteriaBuilder().createQuery(Deliveries.class);
        criteria.from(Deliveries.class);
        List<Deliveries> deliveries = session.createQuery(criteria).getResultList();
        session.close();
//        if (deliveries.size() == 0) {
//            return null;
//        }
        return deliveries;
    }
}
