package web_prak.java.DAO;

import web_prak.java.classes.Supplies_list;

import java.util.List;

public interface Supplies_listDAO {
    void createSuppliesList(Supplies_list supplies_list);
    void updateSuppliesList(Supplies_list supplies_list);
    void deleteSuppliesList(Supplies_list supplies_list);

    Supplies_list getSuppliesListById(long supplies_list_id);
    List<Supplies_list> getSuppliesListByProduct(long product_id);
    List<Supplies_list> getSuppliesListBySupplies(long supply_id);
    List<Supplies_list> getSuppliesListByQuantity(int quantity);
    List<Supplies_list> getSuppliesListMoreQuantity(int quantity);
    List<Supplies_list> getSuppliesListLessQuantity(int quantity);
    List<Supplies_list> getSuppliesListAll();
}
