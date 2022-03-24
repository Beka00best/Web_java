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
    List<Products> getProductByDate(Date date);
    List<Products> getProductBySupplier(Suppliers supplier);
    List<Products> getProductByDateBefore(Date date);
    List<Products> getProductByDateAfter(Date date);
    List<Products> getAllProduct();
}
