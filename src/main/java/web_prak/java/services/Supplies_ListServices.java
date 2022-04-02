package web_prak.java.services;

import web_prak.java.DAO.Impl.Supplies_listDAOImpl;
import web_prak.java.DAO.Supplies_listDAO;
import web_prak.java.classes.Supplies_list;

import java.util.List;

public class Supplies_ListServices {
    public final Supplies_listDAO supplies_listDAO = new Supplies_listDAOImpl();

    public void createSuppliesList(Supplies_list supplies_list) {
        supplies_listDAO.createSuppliesList(supplies_list);
    }

    public void updateSuppliesList(Supplies_list supplies_list) {
        supplies_listDAO.updateSuppliesList(supplies_list);
    }

    public void deleteSuppliesList(Supplies_list supplies_list) {
        supplies_listDAO.deleteSuppliesList(supplies_list);
    }

    public Supplies_list getSuppliesListById(long supplies_list_id) {
        return supplies_listDAO.getSuppliesListById(supplies_list_id);
    }

    public List<Supplies_list> getSuppliesListByProduct(long product_id) {
        return supplies_listDAO.getSuppliesListByProduct(product_id);
    }

    public List<Supplies_list> getSuppliesListBySupplies(long supply_id) {
        return supplies_listDAO.getSuppliesListBySupplies(supply_id);
    }

    public List<Supplies_list> getSuppliesListByQuantity(int quantity) {
        return supplies_listDAO.getSuppliesListByQuantity(quantity);
    }

    public List<Supplies_list> getSuppliesListMoreQuantity(int quantity) {
        return supplies_listDAO.getSuppliesListMoreQuantity(quantity);
    }

    public List<Supplies_list> getSuppliesListLessQuantity(int quantity) {
        return supplies_listDAO.getSuppliesListLessQuantity(quantity);
    }

    public List<Supplies_list> getSuppliesListAll() {
        return supplies_listDAO.getSuppliesListAll();
    }
}
