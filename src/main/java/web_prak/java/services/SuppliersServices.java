package web_prak.java.services;

import web_prak.java.DAO.Impl.SuppliersDAOImpl;
import web_prak.java.DAO.SuppliersDAO;
import web_prak.java.classes.Suppliers;

import java.util.List;

public class SuppliersServices {
    private final SuppliersDAO suppliersDAO = new SuppliersDAOImpl();

    public void createSuppliers(Suppliers suppliers){
        suppliersDAO.createSuppliers(suppliers);
    }

    public void updateSuppliers(Suppliers suppliers){
        suppliersDAO.updateSuppliers(suppliers);
    }

    public void deleteSuppliers(Suppliers suppliers){
        suppliersDAO.deleteSuppliers(suppliers);
    }

    public Suppliers getSuppliersById(long supplier_id){
        return suppliersDAO.getSuppliersById(supplier_id);
    }

    public List<Suppliers>  getSuppliersByName(String supplier_name){
        return suppliersDAO.getSuppliersByName(supplier_name);
    }

    public List<Suppliers> getSuppliersAll(){
        return suppliersDAO.getSuppliersAll();
    }
}
