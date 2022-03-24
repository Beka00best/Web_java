package web_prak.java.DAO;

import web_prak.java.classes.Deliveries;

import java.sql.Date;
import java.util.List;

public interface DeliveriesDAO {
    void createDeliveries(Deliveries deliveries);
    void updateDeliveries(Deliveries deliveries);
    void deleteDeliveries(Deliveries deliveries);
    Deliveries getDeliveriesById(long delivery_id);
    List<Deliveries> getDeliveriesByClient(long client_id);
    List<Deliveries> getDeliveriesDate(Date data_issue);
    List<Deliveries> getDeliveriesDateAfter(Date data_issue);
    List<Deliveries> getDeliveriesDateBefore(Date data_issue);
    List<Deliveries> getDeliveriesByStatus(String status);
    List<Deliveries> getDeliveriesAll();
}
