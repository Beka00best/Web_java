package web_prak.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web_prak.java.classes.Clients;
import web_prak.java.services.ClientsServices;

import java.util.List;

@Controller
public class ClientsControllers {
    ClientsServices clientsServices = new ClientsServices();

    @GetMapping("/clients")
    public String clients(Model model) {
        List<Clients> clients = clientsServices.getClientsAll();
        model.addAttribute("clients", clients);
        return "Clients";
    }

    @GetMapping("/add-new-client")
    public String addClientPage() {
        return "AddNewClient";
    }

    @PostMapping("/added-cast")
    public String newClient(@RequestParam(name = "client_name") String client_name,
                            @RequestParam(name = "contact") String contact,
                            @RequestParam(name = "address") String address,
                            @RequestParam(name = "email") String email,
                            Model model) {
        Clients new_client;
        new_client = new Clients(client_name, contact, address, email);
        clientsServices.createClient(new_client);
        return "redirect:/clients";
    }

    @GetMapping("/edit-client")
    public String clientEdit(@RequestParam(name = "client_id", required = true) long client_id, Model model) {
        Clients cast = clientsServices.getClientById(client_id);
        model.addAttribute("client", cast);
        return "EditClient";
    }

    @PostMapping("/save-edit-client")
    public String editClient(@RequestParam(name = "client_name") String client_name,
                             @RequestParam(name = "contact") String contact,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "client_id") long client_id,
                             Model model) {
        Clients oldClient = clientsServices.getClientById(client_id);

        oldClient.setClient_name(client_name);
        oldClient.setContact(contact);
        oldClient.setAddress(address);
        oldClient.setEmail(email);

        clientsServices.updateClient(oldClient);

        return "redirect:/clients";
    }

    @GetMapping("delete-client")
    public String deleteClient(@RequestParam(name = "client_id") long client_id, Model model) {
        Clients uselessClient = clientsServices.getClientById(client_id);
        clientsServices.deleteClient(uselessClient);

        return "redirect:/clients";
    }
}
