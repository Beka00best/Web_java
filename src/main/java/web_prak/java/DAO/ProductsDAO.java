package web_prak.java.DAO;

import web_prak.java.classes.Products;
import web_prak.java.classes.Suppliers;

import java.sql.Date;
import java.util.List;

public interface ProductsDAO {
    void createProduct(Products product);
    void updateProduct(Products product);
    void deleteProduct(Products product);
    Products getProductById(long product_id);
    List<Products> getProductByProductName(String product_name);
    List<Products> getProductType(String type);
    List<Products> getProductByExpirationDateFrom(Date date);
    List<Products> getProductBySupplier(Suppliers supplier);
    List<Products> getProductByExpirationDateFromBefore(Date date);
    List<Products> getProductByExpirationDateFromAfter(Date date);
    List<Products> getProductByExpirationDateTo(Date date);
    List<Products> getAllProduct();
}
