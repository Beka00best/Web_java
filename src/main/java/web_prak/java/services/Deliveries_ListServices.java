package web_prak.java.services;

import web_prak.java.DAO.Deliveries_listDAO;
import web_prak.java.DAO.Impl.Deliveries_listDAOImpl;
import web_prak.java.classes.Deliveries_list;

import java.util.List;

public class Deliveries_ListServices {
    public final Deliveries_listDAO deliveries_listDAO = new Deliveries_listDAOImpl();

    public void createDeliveriesList(Deliveries_list deliveries_list) {
        deliveries_listDAO.createDeliveriesList(deliveries_list);
    }

    public void updateDeliveriesList(Deliveries_list deliveries_list) {
        deliveries_listDAO.updateDeliveriesList(deliveries_list);
    }

    public void deleteDeliveriesList(Deliveries_list deliveries_list) {
        deliveries_listDAO.deleteDeliveriesList(deliveries_list);
    }

    public Deliveries_list getDeliveriesListById(long deliveries_list_id) {
        return deliveries_listDAO.getDeliveriesListById(deliveries_list_id);
    }

    public List<Deliveries_list> getDeliveriesListByProduct(long product_id) {
        return deliveries_listDAO.getDeliveriesListByProduct(product_id);
    }

    public List<Deliveries_list> getDeliveriesListByDelivery(long delivery_id) {
        return deliveries_listDAO.getDeliveriesListByDelivery(delivery_id);
    }

    public List<Deliveries_list> getDeliveriesListByQuantity(int quantity) {
        return deliveries_listDAO.getDeliveriesListByQuantity(quantity);
    }

    public List<Deliveries_list> getDeliveriesListMoreQuantity(int quantity) {
        return deliveries_listDAO.getDeliveriesListMoreQuantity(quantity);
    }

    public List<Deliveries_list> getDeliveriesListLessQuantity(int quantity) {
        return deliveries_listDAO.getDeliveriesListLessQuantity(quantity);
    }

    public List<Deliveries_list> getDeliveriesListAll() {
        return deliveries_listDAO.getDeliveriesListAll();
    }
}
