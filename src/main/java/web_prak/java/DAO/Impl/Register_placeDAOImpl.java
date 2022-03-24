package web_prak.java.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web_prak.java.DAO.Register_placeDAO;
import web_prak.java.classes.Register_place;
import web_prak.java.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Register_placeDAOImpl implements Register_placeDAO {
    @Override
    public void createRegPlace(Register_placeDAO register_place) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(register_place);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateRegPlace(Register_placeDAO register_place) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(register_place);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteRegPlace(Register_placeDAO register_place) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(register_place);
        tx1.commit();
        session.close();
    }

    @Override
    public Register_place getRegPlaceById(long place_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Register_place register_place = session.get(Register_place.class, place_id);
        session.close();
        return register_place;
    }

    @Override
    public List<Register_place> getRegPlaceByFree(String free) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Register_place> query = session.createQuery("FROM register_place WHERE free = :param",
                        Register_place.class).setParameter("param", free);
        List<Register_place> register_place = query.getResultList();
        session.close();
        if (register_place.size() == 0) {
            return null;
        }
        return register_place;
    }

    @Override
    public List<Register_place> getRegPlaceByStorageLoc(String storage_location) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Register_place> query = session.createQuery("FROM register_place WHERE storage_location = " +
                        ":param", Register_place.class).setParameter("param", storage_location);
        List<Register_place> register_place = query.getResultList();
        session.close();
        if (register_place.size() == 0) {
            return null;
        }
        return register_place;
    }

    @Override
    public List<Register_place> getAllRegPlace() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Register_place> criteria = session.getCriteriaBuilder().createQuery(Register_place.class);
        criteria.from(Register_place.class);
        List<Register_place> register_place = session.createQuery(criteria).getResultList();
        session.close();
        return register_place;
    }
}
