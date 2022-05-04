package systemTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import web_prak.java.classes.*;
import web_prak.java.services.*;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class SeleniumTests {
    protected final String appURL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setAppURL() {
        final String ffDriver = "/usr/local/bin";
        System.setProperty("webdriver.firefox.marionette", ffDriver);
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1200, 1000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void CheckProductInfo(Products products, String text) {
        Assert.assertTrue(text.contains(products.getProduct_name()));
        Assert.assertTrue(text.contains(products.getType()));
        Assert.assertTrue(text.contains(String.valueOf(products.getQuantity()) + ' ' + products.getMeasure()));
        Assert.assertTrue(text.contains("2021-03-01"));
        Assert.assertTrue(text.contains("2022-03-01"));
        Assert.assertTrue(text.contains(products.getPlace_id().getStorage_location()));
    }

    public void CheckSuppliersInfo(Suppliers suppliers, String text) {
        Assert.assertTrue(text.contains(suppliers.getSupplier_name()));
        Assert.assertTrue(text.contains(suppliers.getContact()));
        Assert.assertTrue(text.contains(suppliers.getAddress()));
        Assert.assertTrue(text.contains(suppliers.getEmail()));
    }

    public void CheckClientsInfo(Clients clients, String text) {
        Assert.assertTrue(text.contains(clients.getClient_name()));
        Assert.assertTrue(text.contains(clients.getContact()));
        Assert.assertTrue(text.contains(clients.getAddress()));
        Assert.assertTrue(text.contains(clients.getEmail()));
    }

    public void CheckDeliveriesInfo(Deliveries_list deliveries_list, String text) {
        Assert.assertTrue(text.contains(String.valueOf(deliveries_list.getProduct_id().getProduct_id())));
        Assert.assertTrue(text.contains(String.valueOf(deliveries_list.getDelivery_id().getClient_id().getClient_id())));
        Assert.assertTrue(text.contains(String.valueOf(deliveries_list.getDelivery_id().getDate_issue())));
        Assert.assertTrue(text.contains(String.valueOf(deliveries_list.getQuantity())));
        Assert.assertTrue(text.contains(deliveries_list.getDelivery_id().getStatus()));
    }

    public void CheckSupplyInfo(Supplies_list supplies_list, String text) {
        Assert.assertTrue(text.contains(String.valueOf(supplies_list.getProduct_id().getProduct_id())));
        Assert.assertTrue(text.contains(String.valueOf(supplies_list.getSupply_id().getSupplier_id().getSupplier_id())));
        Assert.assertTrue(text.contains(String.valueOf(supplies_list.getSupply_id().getData_supply())));
        Assert.assertTrue(text.contains(String.valueOf(supplies_list.getQuantity())));
        Assert.assertTrue(text.contains(supplies_list.getSupply_id().getStatus()));
        Assert.assertTrue(text.contains(String.valueOf(supplies_list.getSupply_id().getStore_period())));
    }

    @Test(priority = 1)
    void productAddEditDeleteTest() {
        Register_placeServices register_placeServices = new Register_placeServices();
        Register_place register_place = register_placeServices.getRegPlaceById(9);
        Products new_product = new Products(1, "Wonder", "виниловые пластинки", 100,
                "штук", Date.valueOf("2021-03-01"), Date.valueOf("2022-03-01"), register_place);
        driver.get(appURL);
        Assert.assertEquals(driver.getTitle(), "Главная страница");

        driver.findElement(By.id("mainProduct")).click();
        Assert.assertEquals(driver.getTitle(), "Товары");

        /* testing adding a product*/
        driver.findElement(By.id("addProduct")).click();
        Assert.assertEquals(driver.getTitle(), "Добавление продукта");
        driver.findElement(By.name("product_name")).sendKeys(new_product.getProduct_name());
        driver.findElement(By.name("type")).sendKeys(new_product.getType());
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(new_product.getQuantity()));
        driver.findElement(By.name("measure")).sendKeys(new_product.getMeasure());
        driver.findElement(By.name("expiration_date_from")).sendKeys("2021-03-01");
        driver.findElement(By.name("expiration_date_to")).sendKeys("2022-03-01");
        driver.findElement(By.name("place_id")).sendKeys(String.valueOf(new_product.getPlace_id().getPlace_id()));
        driver.findElement(By.id("submitAdd")).click();
        Assert.assertEquals(driver.getTitle(), "Товары");
        CheckProductInfo(new_product, driver.findElement(By.id("infoProduct")).getText());

        /* testing editing a product*/
        int new_quantity = 50;
        driver.get("http://localhost:8080/edit-products?product_id=" + new_product.getProduct_id());
        new_product.setQuantity(new_quantity);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(new_product.getQuantity()));
        driver.findElement(By.tagName("button")).click();
        Assert.assertEquals(driver.getTitle(), "Товары");
        CheckProductInfo(new_product, driver.findElement(By.id("infoProduct")).getText());

        /* testing deleting a product */
        driver.get("http://localhost:8080/edit-products?product_id=" + new_product.getProduct_id());
        driver.findElement(By.id("deleteBtn")).click();
        Assert.assertEquals(driver.getTitle(), "Товары");
    }

    @Test(priority = 2)
    void supplierAddEditDeleteTest() {
        Suppliers new_supplier = new Suppliers(1, "Алукард Фон Неймон",
                "5(146)666-66 6", "Кастльвания, пр. Дракулы, 10", "alucardFN@castlvania.com");

        driver.get(appURL);
        Assert.assertEquals(driver.getTitle(), "Главная страница");

        driver.findElement(By.id("mainSupplier")).click();
        Assert.assertEquals(driver.getTitle(), "Поставщики");

        /* testing adding a supplier */
        driver.findElement(By.id("addSupplier")).click();
        Assert.assertEquals(driver.getTitle(), "Добавить нового поставщика");
        driver.findElement(By.name("supplier_name")).sendKeys(new_supplier.getSupplier_name());
        driver.findElement(By.name("contact")).sendKeys(new_supplier.getContact());
        driver.findElement(By.name("address")).sendKeys(new_supplier.getAddress());
        driver.findElement(By.name("email")).sendKeys(new_supplier.getEmail());
        driver.findElement(By.id("submitAddSupp")).click();
        Assert.assertEquals(driver.getTitle(), "Поставщики");
        CheckSuppliersInfo(new_supplier, driver.findElement(By.id("infoSupplier")).getText());

        /* testing editing a supplier */
        String new_email = "alucardVamp@castlvania.com";
        driver.get("http://localhost:8080/edit-supplier?supplier_id=" + new_supplier.getSupplier_id());
        new_supplier.setEmail(new_email);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(new_supplier.getEmail());
        driver.findElement(By.tagName("button")).click();
        Assert.assertEquals(driver.getTitle(), "Поставщики");
        CheckSuppliersInfo(new_supplier, driver.findElement(By.id("infoSupplier")).getText());

        /* testing deleting a supplier*/
        driver.get("http://localhost:8080/edit-supplier?supplier_id=" + new_supplier.getSupplier_id());
        driver.findElement(By.id("deleteBtn")).click();
        Assert.assertEquals(driver.getTitle(), "Поставщики");
    }

    @Test(priority = 3)
    void clientAddEditDeleteTest() {
        Clients new_clients = new Clients(1, "Алукард Фон Неймон",
                "5(146)666-66 6", "Кастльвания, пр. Дракулы, 10", "alucardFN@castlvania.com");

        driver.get(appURL);
        Assert.assertEquals(driver.getTitle(), "Главная страница");

        driver.findElement(By.id("mainClient")).click();
        Assert.assertEquals(driver.getTitle(), "Клиенты");

        /* testing adding a client */
        driver.findElement(By.id("addClient")).click();
        Assert.assertEquals(driver.getTitle(), "Добавить нового клиента");
        driver.findElement(By.name("client_name")).sendKeys(new_clients.getClient_name());
        driver.findElement(By.name("contact")).sendKeys(new_clients.getContact());
        driver.findElement(By.name("address")).sendKeys(new_clients.getAddress());
        driver.findElement(By.name("email")).sendKeys(new_clients.getEmail());
        driver.findElement(By.id("submitAddSupp")).click();
        Assert.assertEquals(driver.getTitle(), "Клиенты");
        CheckClientsInfo(new_clients, driver.findElement(By.id("infoClient")).getText());

        /* testing editing a client */
        String new_email = "alucardVamp@castlvania.com";
        driver.get("http://localhost:8080/edit-client?client_id=" + new_clients.getClient_id());
        new_clients.setEmail(new_email);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(new_clients.getEmail());
        driver.findElement(By.tagName("button")).click();
        Assert.assertEquals(driver.getTitle(), "Клиенты");
        CheckClientsInfo(new_clients, driver.findElement(By.id("infoClient")).getText());

        /* testing deleting a client*/
        driver.get("http://localhost:8080/edit-client?client_id=" + new_clients.getClient_id());
        driver.findElement(By.id("deleteBtn")).click();
        Assert.assertEquals(driver.getTitle(), "Клиенты");
    }

    @Test(priority = 4)
    void supplyAddEditDeleteTest() {
        Register_placeServices register_placeServices = new Register_placeServices();
        Register_place register_place = register_placeServices.getRegPlaceById(9);
        Products new_product = new Products(1, "Wonder", "виниловые пластинки", 100,
                "штук", Date.valueOf("2021-03-01"), Date.valueOf("2022-03-01"), register_place);
        ProductsServices productsServices = new ProductsServices();
        productsServices.createProduct(new_product);

        Suppliers new_supplier = new Suppliers(1, "Алукард Фон Неймон",
                "5(146)666-66 6", "Кастльвания, пр. Дракулы, 10", "alucardFN@castlvania.com");
        SuppliersServices suppliersServices = new SuppliersServices();
        suppliersServices.createSuppliers(new_supplier);

        Supplies new_supplies = new Supplies(1, new_supplier, Date.valueOf("2022-03-01"), "в процессе", 125);
        SuppliesServices suppliesServices = new SuppliesServices();
        suppliesServices.createSupplies(new_supplies);

        Supplies_list supplies_list = new Supplies_list(1, new_product, new_supplies, 60);


        driver.get(appURL);
        Assert.assertEquals(driver.getTitle(), "Главная страница");

        driver.findElement(By.id("mainSupplies")).click();
        Assert.assertEquals(driver.getTitle(), "Поставка");

        /* testing adding a supply */
        driver.findElement(By.id("addSupplies")).click();
        Assert.assertEquals(driver.getTitle(), "Добавить доставку");
        driver.findElement(By.name("product_id")).sendKeys(String.valueOf(supplies_list.getProduct_id().getProduct_id()));
        driver.findElement(By.name("supplier_id")).sendKeys(String.valueOf(supplies_list.getSupply_id().getSupplier_id().getSupplier_id()));
        driver.findElement(By.name("data_supply")).sendKeys(String.valueOf(supplies_list.getSupply_id().getData_supply()));
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(supplies_list.getQuantity()));
        driver.findElement(By.name("status")).sendKeys(supplies_list.getSupply_id().getStatus());
        driver.findElement(By.name("store_period")).sendKeys(String.valueOf(supplies_list.getSupply_id().getStore_period()));
        driver.findElement(By.id("submitAdd")).click();
        Assert.assertEquals(driver.getTitle(), "Поставка");
        CheckSupplyInfo(supplies_list, driver.findElement(By.id("infoSupply")).getText());

        /* testing editing a supply*/
        int new_quantity = 50;
        driver.get("http://localhost:8080/edit-supplies?supplies_list_id=" + supplies_list.getSupplies_list_id());
        supplies_list.setQuantity(new_quantity);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(supplies_list.getQuantity()));
        driver.findElement(By.tagName("button")).click();
        Assert.assertEquals(driver.getTitle(), "Поставка");
        CheckSupplyInfo(supplies_list, driver.findElement(By.id("infoSupply")).getText());

        /* testing deleting a supplier*/
        driver.get("http://localhost:8080/edit-supplies?supplies_list_id=" + supplies_list.getSupplies_list_id());
        driver.findElement(By.id("deleteBtn")).click();
        Assert.assertEquals(driver.getTitle(), "Поставка");
        productsServices.deleteProduct(new_product);
        suppliesServices.deleteSupplies(new_supplies);
        suppliersServices.deleteSuppliers(new_supplier);
        register_place.setFree("да");
    }

    @Test(priority = 5)
    void deliveryAddEditDeleteTest() {
        Register_placeServices register_placeServices = new Register_placeServices();
        Register_place register_place = register_placeServices.getRegPlaceById(9);
        Products new_product = new Products(1, "Wonder", "виниловые пластинки", 100,
                "штук", Date.valueOf("2021-03-01"), Date.valueOf("2022-03-01"), register_place);
        ProductsServices productsServices = new ProductsServices();
        productsServices.createProduct(new_product);

        ClientsServices clientsServices = new ClientsServices();
        Clients new_clients = new Clients(1, "Алукард Фон Неймон",
                "5(146)666-66 6", "Кастльвания, пр. Дракулы, 10", "alucardFN@castlvania.com");
        clientsServices.createClient(new_clients);

        DeliveriesServices deliveriesServices = new DeliveriesServices();
        Deliveries new_deliveries = new Deliveries(1, new_clients, Date.valueOf("2021-03-01"), "в процессе");
        deliveriesServices.createDeliveries(new_deliveries);

        Deliveries_list deliveries_list = new Deliveries_list(1, new_product, new_deliveries, 60);

        driver.get(appURL);
        Assert.assertEquals(driver.getTitle(), "Главная страница");

        driver.findElement(By.id("mainDelivery")).click();
        Assert.assertEquals(driver.getTitle(), "Выдача");

        /* testing adding a delivery*/
        driver.findElement(By.id("addDelivery")).click();
        Assert.assertEquals(driver.getTitle(), "Добавление доставки");
        driver.findElement(By.name("product_id")).sendKeys(String.valueOf(deliveries_list.getProduct_id().getProduct_id()));
        driver.findElement(By.name("client_id")).sendKeys(String.valueOf(deliveries_list.getDelivery_id().getClient_id().getClient_id()));
        driver.findElement(By.name("date_issue")).sendKeys(String.valueOf(deliveries_list.getDelivery_id().getDate_issue()));
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(deliveries_list.getQuantity()));
        driver.findElement(By.name("status")).sendKeys(deliveries_list.getDelivery_id().getStatus());
        driver.findElement(By.id("submitAdd")).click();
        Assert.assertEquals(driver.getTitle(), "Выдача");
        CheckDeliveriesInfo(deliveries_list, driver.findElement(By.id("infoDelivery")).getText());

        /* testing editing a delivery*/
        int new_quantity = 50;
        driver.get("http://localhost:8080/edit-delivery?deliveries_list_id=" + deliveries_list.getDeliveries_list_id());
        deliveries_list.setQuantity(new_quantity);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys(String.valueOf(deliveries_list.getQuantity()));
        driver.findElement(By.tagName("button")).click();
        Assert.assertEquals(driver.getTitle(), "Выдача");
        CheckDeliveriesInfo(deliveries_list, driver.findElement(By.id("infoDelivery")).getText());

        /* testing deleting a delivery*/
        driver.get("http://localhost:8080/edit-delivery?deliveries_list_id=" + deliveries_list.getDeliveries_list_id());
        driver.findElement(By.id("deleteBtn")).click();
        Assert.assertEquals(driver.getTitle(), "Выдача");
        productsServices.deleteProduct(new_product);
        deliveriesServices.deleteDeliveries(new_deliveries);
        clientsServices.deleteClient(new_clients);
        register_place.setFree("да");
    }

    @AfterClass
    public void end() {
        driver.close();
    }
}
