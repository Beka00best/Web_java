package web_prak.java.DAO;

import web_prak.java.classes.Clients;

import java.util.List;

public interface ClientsDAO {
    void createClient(Clients client);
    void updateClient(Clients client);
    void deleteClient(Clients client);

    List<Clients> getClientByName(String client_name);
    Clients getClientById(long client_id);
    List<Clients> getClientsAll();
}
