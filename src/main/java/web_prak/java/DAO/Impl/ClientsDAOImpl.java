package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.ClientsDAO;
import web_prak.java.classes.Clients;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ClientsDAOImpl implements ClientsDAO {
    @Override
    public void createClient(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateClient(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteClient(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Clients> getClientByName(String client_name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Clients> query = session.createQuery("From Clients Where client_name =: param",
                Clients.class).setParameter("param", client_name);
        return query.getResultList();
    }

    @Override
    public Clients getClientById(long client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Clients client = session.get(Clients.class, client_id);
        session.close();
        return client;
    }

    @Override
    public List<Clients> getClientsAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Clients> criteriaQuery = session.getCriteriaBuilder().createQuery(Clients.class);
        criteriaQuery.from(Clients.class);
        List<Clients> data = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return data;
    }
}
