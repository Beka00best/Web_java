package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.Supplies_listDAO;
import web_prak.java.classes.Supplies_list;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Supplies_listDAOImpl implements Supplies_listDAO {
    @Override
    public void createSuppliesList(Supplies_list supplies_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(supplies_list);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateSuppliesList(Supplies_list supplies_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(supplies_list);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteSuppliesList(Supplies_list supplies_list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(supplies_list);
        tx1.commit();
        session.close();
    }

    @Override
    public Supplies_list getSuppliesListById(long supplies_list_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Supplies_list supplies_list = session.get(Supplies_list.class, supplies_list_id);
        session.close();
        return supplies_list;
    }

    @Override
    public List<Supplies_list> getSuppliesListByProduct(long product_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies_list> query = session.createQuery("SELECT d FROM Supplies_list d JOIN " +
                        "d.product_id s WHERE s.product_id = :param", Supplies_list.class)
                .setParameter("param", product_id);
        List<Supplies_list> supplies_lists = query.getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }

    @Override
    public List<Supplies_list> getSuppliesListBySupplies(long supply_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies_list> query = session.createQuery("SELECT d FROM Supplies_list d JOIN " +
                        "d.supply_id s WHERE s.supply_id = :param", Supplies_list.class)
                .setParameter("param", supply_id);
        List<Supplies_list> supplies_lists = query.getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }

    @Override
    public List<Supplies_list> getSuppliesListByQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies_list> query = session.createQuery("FROM Supplies_list WHERE quantity = :param",
                Supplies_list.class).setParameter("param", quantity);
        List<Supplies_list> supplies_lists = query.getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }

    @Override
    public List<Supplies_list> getSuppliesListMoreQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies_list> query = session.createQuery("FROM Supplies_list WHERE quantity <= :param",
                Supplies_list.class).setParameter("param", quantity);
        List<Supplies_list> supplies_lists = query.getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }

    @Override
    public List<Supplies_list> getSuppliesListLessQuantity(int quantity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies_list> query = session.createQuery("FROM Supplies_list WHERE quantity >= :param",
                Supplies_list.class).setParameter("param", quantity);
        List<Supplies_list> supplies_lists = query.getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }

    @Override
    public List<Supplies_list> getSuppliesListAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Supplies_list> criteria = session.getCriteriaBuilder().createQuery(Supplies_list.class);
        criteria.from(Supplies_list.class);
        List<Supplies_list> supplies_lists = session.createQuery(criteria).getResultList();
        session.close();
//        if (supplies_lists.size() == 0) {
//            return null;
//        }
        return supplies_lists;
    }
}
