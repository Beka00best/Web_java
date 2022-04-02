import org.testng.Assert;
import org.testng.annotations.Test;
import web_prak.java.classes.Clients;
import web_prak.java.services.ClientsServices;

import java.util.List;

import static org.testng.Assert.*;

public class ClientsTest {
    private ClientsServices clientsServices;
    private Clients expectedClient;

    @Test
    public void testCreateNewClient() {
        clientsServices = new ClientsServices();
        expectedClient = new Clients("Иван Иванов", "+7 (976) 130-93-80",
                "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(expectedClient);

        Clients realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient, realClient);

        clientsServices.deleteClient(expectedClient);
    }

    @Test
    public void testUpdateClient() {
        clientsServices = new ClientsServices();
        expectedClient = new Clients("Василиса Угаройко", "+7 (975) 135-96-90",
                "Мосфильмовская улица, д53", "jcess1@mashable.com");
        clientsServices.createClient(expectedClient);

        Clients realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient, realClient);

        expectedClient.setAddress("Достоевского д17");
        clientsServices.updateClient(expectedClient);
        realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient, realClient);

        clientsServices.deleteClient(expectedClient);
    }

    @Test
    public void testDeleteClient() {
        clientsServices = new ClientsServices();
        expectedClient = new Clients("Василиса Угаройко", "+7 (975) 135-96-90",
                "Мосфильмовская улица, д53", "jcess1@mashable.com");
        clientsServices.createClient(expectedClient);

        Clients realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient, realClient);
        clientsServices.deleteClient(expectedClient);

        realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertNull(realClient);
    }

    @Test
    public void testGetClientById() {
        clientsServices = new ClientsServices();
        expectedClient = new Clients("Василиса Угаройко", "+7 (975) 135-96-90",
                "Мосфильмовская улица, д53", "jcess1@mashable.com");
        clientsServices.createClient(expectedClient);

        Clients realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient.getClient_id(), realClient.getClient_id());
        clientsServices.deleteClient(expectedClient);
    }

    @Test
    public void testGetClientByName() {
        clientsServices = new ClientsServices();
        List<Clients> expectedClients = List.of(
                new Clients("Иван Иванов", "+7 (975) 135-96-90","Мосфильмовская улица, д53", "jcess1@mashable.com"),
                new Clients("Иван Иванов", "+7 (976) 130-93-80","Ярославское шоссе д26", "cromaint0@go.com")
        );
        clientsServices.createClient(expectedClients.get(0));
        clientsServices.createClient(expectedClients.get(1));


        List<Clients> realClients = clientsServices.getClientByName("Иван Иванов");
        Assert.assertEquals(realClients, expectedClients);

        assertTrue(expectedClients.contains(realClients.get(0)));
        assertTrue(expectedClients.contains(realClients.get(1)));

        clientsServices.deleteClient(expectedClients.get(0));
        clientsServices.deleteClient(expectedClients.get(1));
    }

    @Test
    public void testGetClientAll() {
        clientsServices = new ClientsServices();
        List<Clients> expectedClients = List.of(
                new Clients("Иван Иванов", "+7 (975) 135-96-90","Мосфильмовская улица, д53", "jcess1@mashable.com"),
                new Clients("Иван Иванов", "+7 (976) 130-93-80","Ярославское шоссе д26", "cromaint0@go.com")
        );
        clientsServices.createClient(expectedClients.get(0));
        clientsServices.createClient(expectedClients.get(1));


        List<Clients> realClients = clientsServices.getClientsAll();
        Assert.assertEquals(realClients, expectedClients);

        assertTrue(expectedClients.contains(realClients.get(0)));
        assertTrue(expectedClients.contains(realClients.get(1)));

        clientsServices.deleteClient(expectedClients.get(0));
        clientsServices.deleteClient(expectedClients.get(1));
    }
}
