package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.SuppliersDAO;
import web_prak.java.classes.Suppliers;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SuppliersDAOImpl implements SuppliersDAO {
    @Override
    public void createSuppliers(Suppliers suppliers) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(suppliers);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateSuppliers(Suppliers suppliers) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(suppliers);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteSuppliers(Suppliers suppliers) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(suppliers);
        tx1.commit();
        session.close();
    }

    @Override
    public Suppliers getSuppliersById(long supplier_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Suppliers suppliers = session.get(Suppliers.class, supplier_id);
        session.close();
        return suppliers;
    }

    @Override
    public List<Suppliers> getSuppliersByName(String supplier_name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Suppliers> query = session.createQuery("From suppliers Where supplier_name LIKE :gotName",
                Suppliers.class).setParameter("gotName", "%" + supplier_name + "%");
        List<Suppliers> suppliers = query.getResultList();
        session.close();
        if (suppliers.size() == 0) {
            return null;
        }
        return suppliers;
    }

    @Override
    public List<Suppliers> getSuppliersAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Suppliers> criteria = session.getCriteriaBuilder().createQuery(Suppliers.class);
        criteria.from(Suppliers.class);
        List<Suppliers> suppliers = session.createQuery(criteria).getResultList();
        session.close();
        return suppliers;
    }
}
