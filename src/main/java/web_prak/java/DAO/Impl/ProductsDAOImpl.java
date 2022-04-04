package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.ProductsDAO;
import web_prak.java.classes.Products;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.List;

public class ProductsDAOImpl implements ProductsDAO {
    @Override
    public void createProduct(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateProduct(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(product);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteProduct(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    @Override
    public Products getProductById(long product_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Products product = session.get(Products.class, product_id);
        session.close();
        return product;
    }

    @Override
    public List<Products> getProductByProductName(String product_name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE product_name = :param",
                        Products.class).setParameter("param", product_name);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getProductType(String type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE type = :param", Products.class)
                .setParameter("param", type);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getProductByExpirationDateFrom(Date date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE expiration_date_from = :param",
                        Products.class).setParameter("param", date);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getProductByExpirationDateFromBefore(Date date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE expiration_date_from <= :param",
                        Products.class).setParameter("param", date);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getProductByExpirationDateFromAfter(Date date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE expiration_date_from >= :param",
                        Products.class).setParameter("param", date);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getProductByExpirationDateTo(Date date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Products> query = session.createQuery("FROM Products WHERE expiration_date_to = :param",
                        Products.class).setParameter("param", date);
        List<Products> products = query.getResultList();
        session.close();
//        if (products.size() == 0) {
//            return null;
//        }
        return products;
    }

    @Override
    public List<Products> getAllProduct() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Products> criteria = session.getCriteriaBuilder().createQuery(Products.class);
        criteria.from(Products.class);
        List<Products> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
