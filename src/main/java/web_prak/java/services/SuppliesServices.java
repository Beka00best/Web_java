package web_prak.java.services;

import web_prak.java.DAO.Impl.SuppliesDAOImpl;
import web_prak.java.DAO.SuppliesDAO;
import web_prak.java.classes.Supplies;

import java.sql.Date;
import java.util.List;

public class SuppliesServices {
    private final SuppliesDAO suppliesDAO = new SuppliesDAOImpl();

    public void createSupplies(Supplies supplies){
        suppliesDAO.createSupplies(supplies);
    }

    public void updateSupplies(Supplies supplies){
        suppliesDAO.updateSupplies(supplies);
    }

    public void deleteSupplies(Supplies supplies){
        suppliesDAO.deleteSupplies(supplies);
    }

    public Supplies getSuppliesById(long supply_id){
        return suppliesDAO.getSuppliesById(supply_id);
    }

    public List<Supplies> getSuppliesBySupplier(long supplier_id){
        return suppliesDAO.getSuppliesBySupplier(supplier_id);
    }

    public List<Supplies> getSuppliesDate(Date data_supply){
        return suppliesDAO.getSuppliesDate(data_supply);
    }

    public List<Supplies> getSuppliesDateAfter(Date data_supply){
        return suppliesDAO.getSuppliesDateAfter(data_supply);
    }

    public List<Supplies> getSuppliesDateBefore(Date data_supply){
        return suppliesDAO.getSuppliesDateBefore(data_supply);
    }

    public List<Supplies> getSuppliesByStatus(String status){
        return suppliesDAO.getSuppliesByStatus(status);
    }

    public List<Supplies> getSuppliesByStorePeriod(int store_period){
        return suppliesDAO.getSuppliesByStorePeriod(store_period);
    }
    public List<Supplies> getSuppliesAll(){
        return suppliesDAO.getSuppliesAll();
    }
}
