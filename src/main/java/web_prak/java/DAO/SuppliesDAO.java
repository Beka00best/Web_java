package web_prak.java.DAO;

import web_prak.java.classes.Supplies;

import java.sql.Date;
import java.util.List;

public interface SuppliesDAO {
    void createSupplies(Supplies supplies);
    void updateSupplies(Supplies supplies);
    void deleteSupplies(Supplies supplies);
    Supplies getSuppliesById(long supply_id);
    List<Supplies> getSuppliesBySupplier(long supplier_id);
    List<Supplies> getSuppliesDate(Date data_supply);
    List<Supplies> getSuppliesDateAfter(Date data_supply);
    List<Supplies> getSuppliesDateBefore(Date data_supply);
    List<Supplies> getSuppliesByStatus(String status);
    List<Supplies> getSuppliesByStorePeriod(int store_period);
    List<Supplies> getSuppliesAll();
}
