import org.testng.annotations.Test;
import web_prak.java.classes.Clients;
import web_prak.java.classes.Deliveries;
import web_prak.java.services.ClientsServices;
import web_prak.java.services.DeliveriesServices;

import java.util.List;

import static org.testng.Assert.*;

public class DeliveriesTest {
    public final DeliveriesServices deliveriesServices = new DeliveriesServices();
    public final ClientsServices clientsServices = new ClientsServices();

    @Test
    public void testCreateNewDeliveries() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries expectedDeliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(expectedDeliveries);

        Deliveries realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertEquals(expectedDeliveries, realDeliveries);

        deliveriesServices.deleteDeliveries(expectedDeliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testUpdateDeliveries() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries expectedDeliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(expectedDeliveries);

        Deliveries realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertEquals(expectedDeliveries, realDeliveries);

        expectedDeliveries.setDate_issue(java.sql.Date.valueOf("2022-08-10"));
        deliveriesServices.updateDeliveries(expectedDeliveries);
        realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertEquals(expectedDeliveries, realDeliveries);

        deliveriesServices.deleteDeliveries(expectedDeliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testDeleteDeliveries() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries expectedDeliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(expectedDeliveries);

        Deliveries realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertEquals(expectedDeliveries, realDeliveries);

        deliveriesServices.deleteDeliveries(expectedDeliveries);
        clientsServices.deleteClient(clients);

        realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertNull(realDeliveries);
    }

    @Test
    public void testGetDeliveriesById() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        Deliveries expectedDeliveries = new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен");
        deliveriesServices.createDeliveries(expectedDeliveries);

        Deliveries realDeliveries = deliveriesServices.getDeliveriesById(expectedDeliveries.getDelivery_id());
        assertEquals(expectedDeliveries.getDelivery_id(), realDeliveries.getDelivery_id());

        deliveriesServices.deleteDeliveries(expectedDeliveries);
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesByClients() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
                );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesByClient(clients.getClient_id());
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesByDate() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
        );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesDate(java.sql.Date.valueOf("2022-02-05"));
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesByDateAfter() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
        );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesDateAfter(java.sql.Date.
                valueOf("2023-02-05"));
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesByDateBefore() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
        );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesDateBefore(java.sql.Date.
                valueOf("2021-02-05"));
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesByStatus() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
        );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesByStatus("завершен");
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }

    @Test
    public void testGetDeliveriesAll() {
        Clients clients = new Clients(1,"Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(clients);
        List<Deliveries> expectedDeliveries = List.of(
                new Deliveries(1, clients, java.sql.Date.valueOf("2022-02-05"), "завершен"),
                new Deliveries(2, clients, java.sql.Date.valueOf("2022-02-05"), "завершен")
        );
        deliveriesServices.createDeliveries(expectedDeliveries.get(0));
        deliveriesServices.createDeliveries(expectedDeliveries.get(1));

        List<Deliveries> realDeliveries = deliveriesServices.getDeliveriesAll();
        assertEquals(expectedDeliveries, realDeliveries);

        assertTrue(expectedDeliveries.contains(realDeliveries.get(0)));
        assertTrue(expectedDeliveries.contains(realDeliveries.get(1)));

        deliveriesServices.deleteDeliveries(expectedDeliveries.get(0));
        deliveriesServices.deleteDeliveries(expectedDeliveries.get(1));
        clientsServices.deleteClient(clients);
    }
}
