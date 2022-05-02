package web_prak.java.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web_prak.java.classes.Products;
import web_prak.java.classes.Suppliers;
import web_prak.java.classes.Supplies;
import web_prak.java.classes.Supplies_list;
import web_prak.java.services.ProductsServices;
import web_prak.java.services.SuppliersServices;
import web_prak.java.services.SuppliesServices;
import web_prak.java.services.Supplies_ListServices;

import java.sql.Date;
import java.util.List;

@Controller
public class SuppliesControllers {
    Supplies_ListServices supplies_listServices = new Supplies_ListServices();
    SuppliesServices suppliesServices = new SuppliesServices();
    SuppliersServices suppliersServices = new SuppliersServices();
    ProductsServices productsServices = new ProductsServices();

    @GetMapping("/supplies")
    public String supplies(Model model) {
        List<Supplies_list> supplies_lists = supplies_listServices.getSuppliesListAll();
        model.addAttribute("supplies_list", supplies_lists);
        return "Supplies";
    }

    @GetMapping("/add-new-supplies")
    public String addSupplyPage() {
        return "AddNewSupply";
    }

    @PostMapping("/added-supply")
    public String newSupply(@RequestParam(name = "product_id") long product_id,
                            @RequestParam(name = "supplier_id") long supplier_id,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "data_supply", required = false) java.util.Date data_supply,
                            @RequestParam(name = "status") String status,
                            @RequestParam(name = "store_period") int store_period,
                            @RequestParam(name = "quantity") int quantity,
                            Model model) {
        Supplies_list new_supplies_list;
        Supplies new_supplies;
        Suppliers suppliers;
        Products products;
        if (product_id > 0 && supplier_id > 0) {
            Date sql_data_supply = new Date(data_supply.getTime());
            suppliers = suppliersServices.getSuppliersById(supplier_id);
            new_supplies = new Supplies(1, suppliers, sql_data_supply, status, store_period);
            suppliesServices.createSupplies(new_supplies);
            products = productsServices.getProductById(product_id);
        } else {
            model.addAttribute("error", "Product and supplier null error");
            return "pageError";
        }
        new_supplies_list = new Supplies_list(1, products, new_supplies, quantity);
        supplies_listServices.createSuppliesList(new_supplies_list);

        Supplies_list tmp = supplies_listServices.getSuppliesListById(new_supplies_list.getSupplies_list_id());
        if(tmp == null) {
            model.addAttribute("error", "Supplies_list create error");
            return "pageError";
        }
        return "redirect:/supplies";
    }

    @GetMapping("/edit-supplies")
    public String suppliesEdit(@RequestParam(name = "supplies_list_id") long supplies_list_id, Model model) {
        Supplies_list supplies_list = supplies_listServices.getSuppliesListById(supplies_list_id);
        model.addAttribute("supply", supplies_list);
        return "EditSupply";
    }

    @PostMapping("/save-edit-supply")
    public String editSupply(@RequestParam(name = "supplies_list_id") long supplies_list_id,
                             @RequestParam(name = "product_id") long product_id,
                             @RequestParam(name = "supplier_id") long supplier_id,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "data_supply", required = false) java.util.Date data_supply,
                             @RequestParam(name = "status") String status,
                             @RequestParam(name = "store_period") int store_period,
                             @RequestParam(name = "quantity") int quantity,
                             Model model) {
        Supplies_list supplies_list = supplies_listServices.getSuppliesListById(supplies_list_id);
        Supplies new_supplies = suppliesServices.getSuppliesById(supplies_list.getSupply_id().getSupply_id());
        Suppliers suppliers;
        Products products;
        if (product_id > 0 && supplier_id > 0) {
            Date sql_data_supply = new Date(data_supply.getTime());
            suppliers = suppliersServices.getSuppliersById(supplier_id);
            new_supplies.setSupplier_id(suppliers);
            new_supplies.setData_supply(sql_data_supply);
            new_supplies.setStatus(status);
            new_supplies.setStore_period(store_period);
            suppliesServices.updateSupplies(new_supplies);
            products = productsServices.getProductById(product_id);
        } else {
            model.addAttribute("error", "Product and supplier null error");
            return "pageError";
        }
        supplies_list.setProduct_id(products);
        supplies_list.setQuantity(quantity);
        supplies_listServices.updateSuppliesList(supplies_list);

        Supplies_list tmp = supplies_listServices.getSuppliesListById(supplies_list.getSupplies_list_id());
        if(tmp == null) {
            model.addAttribute("error", "Supplies_list create error");
            return "pageError";
        }
        return "redirect:/supplies";
    }

    @GetMapping("/delete-supply")
    public String deleteSuppliers_list(@RequestParam(name = "supplies_list_id") long supplies_list_id, Model model) {
        Supplies_list supplies_list = supplies_listServices.getSuppliesListById(supplies_list_id);
        supplies_listServices.deleteSuppliesList(supplies_list);
        return "redirect:/supplies";
    }

    @GetMapping("/filter-supply")
    public String filtration(Model model) {
        return "FiltrationSupplies";
    }

    @PostMapping("/filtrated-supply")
    public String filterProvision(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_after", required = false) java.util.Date date_after,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_before", required = false) java.util.Date date_before,
                                  Model model) {
        List<Supplies> prod_after = suppliesServices.getSuppliesAll();
        List<Supplies> prod_before = suppliesServices.getSuppliesAll();

        if(date_after != null) {
            Date sql_date_after = new Date(date_after.getTime());
            prod_after = suppliesServices.getSuppliesDate(sql_date_after);
        }
        if(date_before != null) {
            Date sql_date_before = new Date(date_before.getTime());
            prod_before = suppliesServices.getSuppliesDate(sql_date_before);
        }

        prod_after.retainAll(prod_before);

        model.addAttribute("filter_supplies", prod_after);

        return "UpdateSupplies";
    }
}
