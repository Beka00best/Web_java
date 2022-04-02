import org.testng.annotations.Test;
import web_prak.java.classes.Products;
import web_prak.java.classes.Register_place;
import web_prak.java.services.ProductsServices;
import web_prak.java.services.Register_placeServices;

import java.util.List;

import static org.testng.Assert.*;


public class ProductsTest {
    private final Register_placeServices register_placeServices = new Register_placeServices();
    private final ProductsServices productsServices = new ProductsServices();

    @Test
    public void testCreateNewProduct() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products expectedProduct = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(expectedProduct);
        Products realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertEquals(expectedProduct, realProducts);
        productsServices.deleteProduct(expectedProduct);
        register_placeServices.deleteRegisterPlace(register_place);
    }

    @Test
    public void testUpdateProduct() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products expectedProduct = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(expectedProduct);
        Products realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertEquals(expectedProduct, realProducts);

        expectedProduct.setType("Ультрабук");
        productsServices.updateProduct(expectedProduct);
        realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertEquals(expectedProduct, realProducts);

        productsServices.deleteProduct(expectedProduct);
        register_placeServices.deleteRegisterPlace(register_place);
    }

    @Test
    public void testDeleteProduct() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products expectedProduct = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(expectedProduct);
        Products realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertEquals(expectedProduct, realProducts);

        productsServices.deleteProduct(expectedProduct);
        register_placeServices.deleteRegisterPlace(register_place);

        realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertNull(realProducts);
    }

    @Test
    public void testGetProductById() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products expectedProduct = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(expectedProduct);
        Products realProducts = productsServices.getProductById(expectedProduct.getProduct_id());
        assertEquals(expectedProduct.getProduct_id(), realProducts.getProduct_id());
        productsServices.deleteProduct(expectedProduct);
        register_placeServices.deleteRegisterPlace(register_place);
    }

    @Test
    public void testGetProductByName() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-07-01"), java.sql.Date.valueOf("2023-09-11"), register_place.get(1))
                );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductByProductName("Asus Rog Strix");
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductByType() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-07-01"), java.sql.Date.valueOf("2023-09-11"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductType("Ноутбуки");
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductByExpirationDateFrom() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2023-09-11"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductByDate(java.sql.Date.valueOf("2021-08-01"));
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductByExpirationDateFromBefore() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2023-09-11"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductByDateBefore(java.sql.Date.valueOf("2022-08-01"));
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductByExpirationDateFromAfter() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2023-09-11"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductByDateAfter(java.sql.Date.valueOf("2020-08-01"));
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductByExpirationDateTo() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getProductByDateTo(java.sql.Date.valueOf("2022-08-01"));
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

    @Test
    public void testGetProductAll() {
        List<Register_place> register_place = List.of(
                new Register_place(1,"02 10 03", "нет"),
                new Register_place(2,"03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(register_place.get(0));
        register_placeServices.createRegisterPlace(register_place.get(1));

        List<Products> expectedProduct = List.of(
                new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(0)),
                new Products(2, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                        java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place.get(1))
        );

        productsServices.createProduct(expectedProduct.get(0));
        productsServices.createProduct(expectedProduct.get(1));

        List<Products> realProducts = productsServices.getAllProduct();
        assertEquals(expectedProduct, realProducts);

        assertTrue(expectedProduct.contains(realProducts.get(0)));
        assertTrue(expectedProduct.contains(realProducts.get(1)));

        productsServices.deleteProduct(expectedProduct.get(0));
        productsServices.deleteProduct(expectedProduct.get(1));
        register_placeServices.deleteRegisterPlace(register_place.get(0));
        register_placeServices.deleteRegisterPlace(register_place.get(1));
    }

}


