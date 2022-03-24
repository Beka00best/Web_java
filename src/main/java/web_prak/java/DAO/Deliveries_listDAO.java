package web_prak.java.DAO;

import web_prak.java.classes.Deliveries_list;

import java.util.List;

public interface Deliveries_listDAO {
    void createDeliveriesList(Deliveries_list deliveries_list);
    void updateDeliveriesList(Deliveries_list deliveries_list);
    void deleteDeliveriesList(Deliveries_list deliveries_list);

    Deliveries_list getDeliveriesListById(long deliveries_list_id);
    List<Deliveries_list> getDeliveriesListByProduct(long product_id);
    List<Deliveries_list> getDeliveriesListByDelivery(long delivery_id);
    List<Deliveries_list> getDeliveriesListByQuantity(int quantity);
    List<Deliveries_list> getDeliveriesListMoreQuantity(int quantity);
    List<Deliveries_list> getDeliveriesListLessQuantity(int quantity);
    List<Deliveries_list> getDeliveriesListAll();
}
