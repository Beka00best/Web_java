package web_prak.java.services;

import web_prak.java.DAO.Impl.ProductsDAOImpl;
import web_prak.java.DAO.ProductsDAO;
import web_prak.java.classes.Products;
import web_prak.java.classes.Suppliers;

import java.sql.Date;
import java.util.List;

public class ProductsServices {
    private final ProductsDAO productsDAO = new ProductsDAOImpl();

    public void createProduct(Products product) {
        productsDAO.createProduct(product);
    }

    public void updateProduct(Products products) {
        productsDAO.updateProduct(products);
    }

    public void deleteProduct(Products products) {
        productsDAO.deleteProduct(products);
    }

    public Products getProductById(long product_id) {
        return productsDAO.getProductById(product_id);
    }

    public List<Products> getProductByProductName(String product_name){
        return productsDAO.getProductByProductName(product_name);
    }

    public List<Products> getProductType(String type) {
        return productsDAO.getProductType(type);
    }

    public List<Products> getProductByDate(Date date) {
        return productsDAO.getProductByExpirationDateFrom(date);
    }

    public List<Products> getProductBySupplier(Suppliers supplier) {
        return productsDAO.getProductBySupplier(supplier);
    }

    public List<Products> getProductByDateBefore(Date date) {
        return productsDAO.getProductByExpirationDateFromBefore(date);
    }

    public List<Products> getProductByDateAfter(Date date) {
        return productsDAO.getProductByExpirationDateFromAfter(date);
    }

    public List<Products> getProductByDateTo(Date date) {
        return productsDAO.getProductByExpirationDateTo(date);
    }

    public List<Products> getAllProduct() {
        return productsDAO.getAllProduct();
    }
}
