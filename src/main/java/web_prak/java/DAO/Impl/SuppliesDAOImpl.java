package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.SuppliesDAO;
import web_prak.java.classes.Supplies;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.List;

public class SuppliesDAOImpl implements SuppliesDAO {
    @Override
    public void createSupplies(Supplies supplies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(supplies);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateSupplies(Supplies supplies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(supplies);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteSupplies(Supplies supplies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(supplies);
        tx1.commit();
        session.close();
    }

    @Override
    public Supplies getSuppliesById(long supply_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Supplies supplies = session.get(Supplies.class, supply_id);
        session.close();
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesBySupplier(long supplier_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("SELECT d FROM Supplies d JOIN d.supplier_id s " +
                        "WHERE s.supplier_id = :param", Supplies.class).setParameter("param", supplier_id);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesDate(Date data_supply) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("FROM Supplies WHERE data_supply = :param",
                Supplies.class).setParameter("param", data_supply);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesDateAfter(Date data_supply) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("FROM Supplies WHERE data_supply <= :param",
                        Supplies.class).setParameter("param", data_supply);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesDateBefore(Date data_supply) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("FROM Supplies WHERE data_supply >= :param",
                Supplies.class).setParameter("param", data_supply);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesByStatus(String status) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("FROM Supplies WHERE status = :param",
                Supplies.class).setParameter("param", status);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesByStorePeriod(int store_period) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Supplies> query = session.createQuery("FROM Supplies WHERE store_period = :param",
                Supplies.class).setParameter("param", store_period);
        List<Supplies> supplies = query.getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }

    @Override
    public List<Supplies> getSuppliesAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Supplies> criteria = session.getCriteriaBuilder().createQuery(Supplies.class);
        criteria.from(Supplies.class);
        List<Supplies> supplies = session.createQuery(criteria).getResultList();
        session.close();
//        if (supplies.size() == 0) {
//            return null;
//        }
        return supplies;
    }
}
