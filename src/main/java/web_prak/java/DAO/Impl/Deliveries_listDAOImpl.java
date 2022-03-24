package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.Deliveries_listDAO;
import web_prak.java.classes.Deliveries_list;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Deliveries_listDAOImpl implements Deliveries_listDAO {
    @Override
    public void createDeliveriesList(Deliveries_list deliveries_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(deliveries_list);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateDeliveriesList(Deliveries_list deliveries_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(deliveries_list);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteDeliveriesList(Deliveries_list deliveries_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(deliveries_list);
        tx1.commit();
        session.close();
    }

    @Override
    public Deliveries_list getDeliveriesListById(long deliveries_list_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Deliveries_list deliveries_list = session.get(Deliveries_list.class, deliveries_list_id);
        session.close();
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListByDelivery(long delivery_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries_list> query = session.createQuery("SELECT d FROM deliveries_list d JOIN " +
                        "dl.delivery_id s WHERE s.delivery_id = :param", Deliveries_list.class)
                .setParameter("param", delivery_id);
        List<Deliveries_list> deliveries_list = query.getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListByProduct(long product_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries_list> query = session.createQuery("SELECT d FROM deliveries_list d JOIN " +
                        "dl.product_id s WHERE s.product_id = :param", Deliveries_list.class)
                .setParameter("param", product_id);
        List<Deliveries_list> deliveries_list = query.getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListByQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries_list> query = session.createQuery("FROM deliveries_list WHERE quantity = :param",
                Deliveries_list.class).setParameter("param", quantity);
        List<Deliveries_list> deliveries_list = query.getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListMoreQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries_list> query = session.createQuery("FROM deliveries_list WHERE quantity >= :param",
                Deliveries_list.class).setParameter("param", quantity);
        List<Deliveries_list> deliveries_list = query.getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListLessQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Deliveries_list> query = session.createQuery("FROM deliveries_list WHERE quantity <= :param",
                Deliveries_list.class).setParameter("param", quantity);
        List<Deliveries_list> deliveries_list = query.getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }

    @Override
    public List<Deliveries_list> getDeliveriesListAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Deliveries_list> criteria = session.getCriteriaBuilder().createQuery(Deliveries_list.class);
        criteria.from(Deliveries_list.class);
        List<Deliveries_list> deliveries_list = session.createQuery(criteria).getResultList();
        session.close();
        if (deliveries_list.size() == 0) {
            return null;
        }
        return deliveries_list;
    }
}
