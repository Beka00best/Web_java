import org.testng.annotations.Test;
import web_prak.java.classes.*;
import web_prak.java.services.*;

import java.util.List;

import static org.testng.Assert.*;

public class DeliveriesListTest {
    public final Register_placeServices register_placeServices = new Register_placeServices();
    public final ProductsServices productsServices = new ProductsServices();
    public final ClientsServices clientsServices = new ClientsServices();
    public final DeliveriesServices deliveriesServices = new DeliveriesServices();
    public final Deliveries_ListServices deliveries_listServices = new Deliveries_ListServices();

    @Test
    public void testCreateNewDeliveries_List() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        Deliveries_list expectedDeliveriesList = new Deliveries_list(1, products, deliveries, 6);
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList);

        Deliveries_list realDeliveriesList = deliveries_listServices.getDeliveriesListById(expectedDeliveriesList.
                getDeliveries_list_id());
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testUpdateDeliveries_List() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        Deliveries_list expectedDeliveriesList = new Deliveries_list(1, products, deliveries, 6);
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList);

        Deliveries_list realDeliveriesList = deliveries_listServices.getDeliveriesListById(expectedDeliveriesList.
                getDeliveries_list_id());
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        expectedDeliveriesList.setQuantity(7);
        deliveries_listServices.updateDeliveriesList(expectedDeliveriesList);
        realDeliveriesList = deliveries_listServices.getDeliveriesListById(expectedDeliveriesList.
                getDeliveries_list_id());
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);

        realDeliveriesList = deliveries_listServices.getDeliveriesListById(expectedDeliveriesList.
                getDeliveries_list_id());
        assertNull(realDeliveriesList);
    }

    @Test
    public void testGetDeliveries_ListById() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        Deliveries_list expectedDeliveriesList = new Deliveries_list(1, products, deliveries, 6);
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList);

        Deliveries_list realDeliveriesList = deliveries_listServices.getDeliveriesListById(expectedDeliveriesList.
                getDeliveries_list_id());
        assertEquals(expectedDeliveriesList.getDeliveries_list_id(), realDeliveriesList.getDeliveries_list_id());

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList);
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesListByProduct() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 6),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListByProduct(products.
                getProduct_id());
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesListByDelivery() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 6),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListByDelivery(deliveries.
                getDelivery_id());
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesListByQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 5),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListByQuantity(5);
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesListByMoreQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 5),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListMoreQuantity(6);
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesListByLessQuantity() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 5),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListLessQuantity(4);
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetAllDeliveriesList() {
        Register_place register_place = new Register_place(1,"02 10 03", "нет");
        register_placeServices.createRegisterPlace(register_place);
        Products products = new Products(1, "Asus Rog Strix", "Ноутбуки", 5, "штук",
                java.sql.Date.valueOf("2021-08-01"), java.sql.Date.valueOf("2022-08-01"), register_place);
        productsServices.createProduct(products);
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries deliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(deliveries);

        List<Deliveries_list> expectedDeliveriesList = List.of(
                new Deliveries_list(1, products, deliveries, 5),
                new Deliveries_list(2, products, deliveries, 5)
        );
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.createDeliveriesList(expectedDeliveriesList.get(1));

        List<Deliveries_list> realDeliveriesList = deliveries_listServices.getDeliveriesListAll();
        assertEquals(expectedDeliveriesList, realDeliveriesList);

        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(0));
        deliveries_listServices.deleteDeliveriesList(expectedDeliveriesList.get(1));
        productsServices.deleteProduct(products);
        register_placeServices.deleteRegisterPlace(register_place);
        deliveriesServices.deleteDeliveries(deliveries);
        clientsServices.deleteClient(clients);
    }




}
