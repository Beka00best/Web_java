package web_prak.java.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web_prak.java.classes.Clients;
import web_prak.java.classes.Deliveries;
import web_prak.java.classes.Deliveries_list;
import web_prak.java.classes.Products;
import web_prak.java.services.ClientsServices;
import web_prak.java.services.DeliveriesServices;
import web_prak.java.services.Deliveries_ListServices;
import web_prak.java.services.ProductsServices;

import java.sql.Date;
import java.util.List;

@Controller
public class DeliveriesController {
    DeliveriesServices deliveriesServices = new DeliveriesServices();
    Deliveries_ListServices deliveries_listServices = new Deliveries_ListServices();
    ClientsServices clientsServices = new ClientsServices();
    ProductsServices productsServices = new ProductsServices();

    @GetMapping("/delivery")
    public String deliveries(Model model) {
        List<Deliveries_list> deliveries_lists = deliveries_listServices.getDeliveriesListAll();
        model.addAttribute("deliveries_list", deliveries_lists);
        return "Deliveries";
    }

    @GetMapping("/add-new-delivery")
    public String addDeliveryPage() {
        return "AddNewDelivery";
    }

    @PostMapping("/added-delivery")
    public String newDelivery(@RequestParam(name = "product_id") long product_id,
                              @RequestParam(name = "client_id") long client_id,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_issue", required = false) java.util.Date date_issue,
                              @RequestParam(name = "status") String status,
                              @RequestParam(name = "quantity") int quantity,
                              Model model) {
        Deliveries_list new_deliveries_list;
        Deliveries new_delivery;
        Clients clients;
        Products products;
        if (product_id > 0 && client_id > 0) {
            Date sql_date_issue = new Date(date_issue.getTime());
            clients = clientsServices.getClientById(client_id);
            new_delivery = new Deliveries(1, clients, sql_date_issue, status);
            deliveriesServices.createDeliveries(new_delivery);
            products = productsServices.getProductById(product_id);
        } else {
            model.addAttribute("error", "Product and client null error");
            return "pageError";
        }
        new_deliveries_list = new Deliveries_list(1, products, new_delivery, quantity);
        deliveries_listServices.createDeliveriesList(new_deliveries_list);

        Deliveries_list tmp = deliveries_listServices.getDeliveriesListById(new_deliveries_list.getDeliveries_list_id());
        if(tmp == null) {
            model.addAttribute("error", "Deliveries_list create error");
            return "pageError";
        }
        return "redirect:/delivery";
    }

    @GetMapping("/edit-delivery")
    public String deliveryEdit(@RequestParam(name = "deliveries_list_id", required = true) long deliveries_list_id, Model model) {
        Deliveries_list deliveries_list = deliveries_listServices.getDeliveriesListById(deliveries_list_id);
        model.addAttribute("delivery", deliveries_list);
        return "EditDelivery";
    }

    @PostMapping("/save-delivery-edit")
    public String editDelivery(@RequestParam(name = "deliveries_list_id") long deliveries_list_id,
                               @RequestParam(name = "product_id") long product_id,
                               @RequestParam(name = "client_id") long client_id,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_issue", required = false) java.util.Date date_issue,
                               @RequestParam(name = "quantity") int quantity,
                               @RequestParam(name = "status") String status,
                               Model model) {
        Deliveries_list deliveries_list = deliveries_listServices.getDeliveriesListById(deliveries_list_id);
        Deliveries new_delivery = deliveriesServices.getDeliveriesById(deliveries_list.getDelivery_id().getDelivery_id());
        Clients clients;
        Products products;
        if (product_id > 0 && client_id > 0) {
            Date sql_date_issue = new Date(date_issue.getTime());
            clients = clientsServices.getClientById(client_id);
            new_delivery.setClient_id(clients);
            new_delivery.setDate_issue(sql_date_issue);
            new_delivery.setStatus(status);
            deliveriesServices.updateDeliveries(new_delivery);
            products = productsServices.getProductById(product_id);
        } else {
            model.addAttribute("error", "Product and client null error");
            return "pageError";
        }
        deliveries_list.setProduct_id(products);
        deliveries_list.setQuantity(quantity);
        deliveries_listServices.updateDeliveriesList(deliveries_list);

        Deliveries_list tmp = deliveries_listServices.getDeliveriesListById(deliveries_list.getDeliveries_list_id());
        if(tmp == null) {
            model.addAttribute("error", "Deliveries_list create error");
            return "pageError";
        }
        return "redirect:/delivery";
    }

    @GetMapping("/delete-delivery")
    public String deleteDelivery_list(@RequestParam(name = "deliveries_list_id") long deliveries_list_id, Model model) {
        Deliveries_list deliveries_list = deliveries_listServices.getDeliveriesListById(deliveries_list_id);
        deliveries_listServices.deleteDeliveriesList(deliveries_list);
        return "redirect:/delivery";
    }

    @GetMapping("/filter-delivery")
    public String filtration(Model model) {
        return "FiltrationDeliveries";
    }

    @PostMapping("/filtrated-del")
    public String filterDelivery(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_after", required = false) java.util.Date date_after,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_before", required = false) java.util.Date date_before,
                                 Model model) {
        List<Deliveries> prod_after = deliveriesServices.getDeliveriesAll();
        List<Deliveries> prod_before = deliveriesServices.getDeliveriesAll();

        if(date_after != null) {
            Date sql_date_after = new Date(date_after.getTime());
            prod_after = deliveriesServices.getDeliveriesDate(sql_date_after);
        }
        if(date_before != null) {
            Date sql_date_before = new Date(date_before.getTime());
            prod_before = deliveriesServices.getDeliveriesDate(sql_date_before);
        }

        prod_after.retainAll(prod_before);

        model.addAttribute("filter_deliveries", prod_after);
        return "UpdateDeliveries";
    }
}
