package web_prak.java.services;

import web_prak.java.DAO.DeliveriesDAO;
import web_prak.java.DAO.Impl.DeliveriesDAOImpl;
import web_prak.java.classes.Deliveries;

import java.sql.Date;
import java.util.List;

public class DeliveriesServices {
    private final DeliveriesDAO deliveriesDAO = new DeliveriesDAOImpl();

    public void createDeliveries(Deliveries deliveries) {
        deliveriesDAO.createDeliveries(deliveries);
    }

    public void updateDeliveries(Deliveries deliveries) {
        deliveriesDAO.updateDeliveries(deliveries);
    }

    public void deleteDeliveries(Deliveries deliveries) {
        deliveriesDAO.deleteDeliveries(deliveries);
    }

    public Deliveries getDeliveriesById(long delivery_id) {
        return deliveriesDAO.getDeliveriesById(delivery_id);
    }

    public List<Deliveries> getDeliveriesByClient(long client_id) {
        return deliveriesDAO.getDeliveriesByClient(client_id);
    }

    public List<Deliveries> getDeliveriesDate(Date data_issue) {
        return deliveriesDAO.getDeliveriesDate(data_issue);
    }

    public List<Deliveries> getDeliveriesDateAfter(Date data_issue) {
        return deliveriesDAO.getDeliveriesDateAfter(data_issue);
    }

    public List<Deliveries> getDeliveriesDateBefore(Date data_issue) {
        return deliveriesDAO.getDeliveriesDateBefore(data_issue);
    }

    public List<Deliveries> getDeliveriesByStatus(String status) {
        return deliveriesDAO.getDeliveriesByStatus(status);
    }

    public List<Deliveries> getDeliveriesAll() {
        return deliveriesDAO.getDeliveriesAll();
    }
}
