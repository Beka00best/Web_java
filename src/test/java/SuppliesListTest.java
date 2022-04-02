import org.testng.annotations.Test;
import web_prak.java.classes.*;
import web_prak.java.services.*;

import java.util.List;

import static org.testng.Assert.*;

public class SuppliesListTest {
    public final Register_placeServices register_placeServices = new Register_placeServices();
    public final ProductsServices productsServices = new ProductsServices();
    public final SuppliersServices suppliersServices = new SuppliersServices();
    public final SuppliesServices suppliesServices = new SuppliesServices();
    public final Supplies_ListServices supplies_listServices = new Supplies_ListServices();

    @Test
    public void testCreateNewSuppliesList() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        Supplies_list expectedSuppliesList = new Supplies_list(1, products, supplies, 3);
        supplies_listServices.createSuppliesList(expectedSuppliesList);

        Supplies_list realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.
                getSupplies_list_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testUpdateSuppliesList() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        Supplies_list expectedSuppliesList = new Supplies_list(1, products, supplies, 3);
        supplies_listServices.createSuppliesList(expectedSuppliesList);

        Supplies_list realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.
                getSupplies_list_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        expectedSuppliesList.setQuantity(5);
        supplies_listServices.updateSuppliesList(expectedSuppliesList);
        realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.getSupplies_list_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testDeleteSuppliesList() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        Supplies_list expectedSuppliesList = new Supplies_list(1, products, supplies, 3);
        supplies_listServices.createSuppliesList(expectedSuppliesList);

        Supplies_list realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.
                getSupplies_list_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);

        realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.getSupplies_list_id());
        assertNull(realSupplies_list);
    }

    @Test
    public void testGetSuppliesListById() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        Supplies_list expectedSuppliesList = new Supplies_list(1, products, supplies, 3);
        supplies_listServices.createSuppliesList(expectedSuppliesList);

        Supplies_list realSupplies_list = supplies_listServices.getSuppliesListById(expectedSuppliesList.
                getSupplies_list_id());
        assertEquals(expectedSuppliesList.getSupplies_list_id(), realSupplies_list.getSupplies_list_id());

        supplies_listServices.deleteSuppliesList(expectedSuppliesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesListByProduct() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 4)
                );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListByProduct(products.
                getProduct_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesListBySupplies() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 4)
        );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListBySupplies(supplies.
                getSupply_id());
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesListByQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 3)
        );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListByQuantity(3);
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesListByMoreQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 3)
        );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListMoreQuantity(4);
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesListByLessQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 3)
        );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListLessQuantity(2);
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetAllSuppliesList() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies supplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(supplies);

        List<Supplies_list> expectedSuppliesList = List.of(
                new Supplies_list(1, products, supplies, 3),
                new Supplies_list(2, products, supplies, 3)
        );

        supplies_listServices.createSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.createSuppliesList(expectedSuppliesList.get(1));

        List<Supplies_list> realSupplies_list = supplies_listServices.getSuppliesListAll();
        assertEquals(expectedSuppliesList, realSupplies_list);

        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(0));
        supplies_listServices.deleteSuppliesList(expectedSuppliesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        suppliesServices.deleteSupplies(supplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

}
