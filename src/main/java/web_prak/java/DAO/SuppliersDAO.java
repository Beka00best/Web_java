package web_prak.java.DAO;

import web_prak.java.classes.Suppliers;

import java.util.List;

public interface SuppliersDAO {
    void createSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    void deleteSuppliers(Suppliers suppliers);
    Suppliers getSuppliersById(long supplier_id);
    List<Suppliers> getSuppliersByName(String supplier_name);
    List<Suppliers> getSuppliersAll();
}
