package web_prak.java.services;

import web_prak.java.DAO.ClientsDAO;
import web_prak.java.DAO.Impl.ClientsDAOImpl;
import web_prak.java.classes.Clients;

import java.util.List;

public class ClientsServices {
    private final ClientsDAO clientsDAO = new ClientsDAOImpl();

    public void createClient(Clients client) {
        clientsDAO.createClient(client);
    }

    public void updateClient(Clients client) {
        clientsDAO.updateClient(client);
    }

    public void deleteClient(Clients client) {
        clientsDAO.deleteClient(client);
    }

    public Clients getClientById(long client_id) {
        return clientsDAO.getClientById(client_id);
    }

    public List<Clients> getClientsAll() {
        return clientsDAO.getClientsAll();
    }

}
