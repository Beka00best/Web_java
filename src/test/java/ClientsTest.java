import org.testng.annotations.Test;
import web_prak.java.classes.Clients;
import web_prak.java.services.ClientsServices;

import static org.testng.Assert.*;

public class ClientsTest {

    @Test
    public void testCreateNewClient() {
        ClientsServices clientsServices = new ClientsServices();
        Clients expectedClient = new Clients("Иван Иванов", "+7 (976) 130-93-80", "Ярославское шоссе д26", "cromaint0@go.com");
        clientsServices.createClient(expectedClient);

        Clients realClient = clientsServices.getClientById(expectedClient.getClient_id());
        assertEquals(expectedClient, realClient);

        clientsServices.deleteClient(expectedClient);
    }
}
