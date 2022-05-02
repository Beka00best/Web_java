package web_prak.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web_prak.java.classes.Suppliers;
import web_prak.java.services.SuppliersServices;

import java.util.List;

@Controller
public class SuppliersControllers {
    SuppliersServices suppliersServices = new SuppliersServices();

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        List<Suppliers> suppliers = suppliersServices.getSuppliersAll();
        model.addAttribute("suppliers", suppliers);
        return "Suppliers";
    }

    @GetMapping("/add-new-supplier")
    public String addSupplierPage() {
        return "AddNewSupplier";
    }

    @PostMapping("/added-supplier")
    public String newSupplier(@RequestParam(name = "supplier_name") String supplier_name,
                              @RequestParam(name = "contact") String contact,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "email") String email,
                              Model model) {
        Suppliers suppliers;

        suppliers = new Suppliers(supplier_name, contact, address, email);
        suppliersServices.createSuppliers(suppliers);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit-supplier")
    public String supplierEdit(@RequestParam(name = "supplier_id", required = true) long supplier_id, Model model) {
        Suppliers suppliers = suppliersServices.getSuppliersById(supplier_id);
        model.addAttribute("supplier", suppliers);
        return "EditSupplier";
    }

    @PostMapping("/save-edit-supplier")
    public String editSupplier(@RequestParam(name = "supplier_name") String supplier_name,
                               @RequestParam(name = "contact") String contact,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "supplier_id") long supplier_id,
                               Model model) {
        Suppliers suppliers = suppliersServices.getSuppliersById(supplier_id);
        suppliers.setContact(contact);
        suppliers.setAddress(address);
        suppliers.setEmail(email);

        suppliersServices.updateSuppliers(suppliers);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete-supplier")
    public String deleteSupplier(@RequestParam(name = "supplier_id") long supplier_id,
                                 Model model) {
        Suppliers suppliers = suppliersServices.getSuppliersById(supplier_id);
        suppliersServices.deleteSuppliers(suppliers);
        return "redirect:/suppliers";
    }
}
