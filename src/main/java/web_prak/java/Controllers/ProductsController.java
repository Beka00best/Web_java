package web_prak.java.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web_prak.java.classes.Products;
import web_prak.java.classes.Register_place;
import web_prak.java.services.ProductsServices;
import web_prak.java.services.Register_placeServices;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class ProductsController {
    ProductsServices productsServices = new ProductsServices();
    Register_placeServices register_placeServices = new Register_placeServices();

    @GetMapping("/products")
    public String product(Model model) {
        List<Products> products = productsServices.getAllProduct();
        model.addAttribute("products", products);
        return "Products";
    }

    @GetMapping("/add-new-product")
    public String addProductPage() {
        return "AddNewProduct";
    }

    @PostMapping("/added-product")
    public String newProduct(@RequestParam(name = "product_name") String product_name,
                             @RequestParam(name = "type") String type,
                             @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "measure") String measure,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "expiration_date_from", required = false) java.util.Date expiration_date_from,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "expiration_date_to", required = false) java.util.Date expiration_date_to,
                             @RequestParam(name = "place_id", defaultValue = "-1") long place_id,
                             Model model) {
        Products new_product;
        Register_place register_place;
        if (place_id > 0) {
            if (register_placeServices.getFreePlace(place_id)) {
                register_place = register_placeServices.getRegPlaceById(place_id);
                if (register_place == null) {
                    model.addAttribute("error", "Not exists place");
                    return "pageError";
                }
                register_place.setFree("нет");
                register_placeServices.updateRegisterPlace(register_place);
            } else {
                model.addAttribute("error", "Place is not free try another place");
                return "pageError";
            }
        } else {
            model.addAttribute("error", "Product create error");
            return "pageError";
        }
        Date sql_expiration_date_from = new Date(expiration_date_from.getTime());
        Date sql_expiration_date_to = new Date(expiration_date_to.getTime());

        new_product = new Products(1, product_name, type, quantity, measure, sql_expiration_date_from, sql_expiration_date_to, register_place);

        productsServices.createProduct(new_product);

        Products tmp = productsServices.getProductById(new_product.getProduct_id());
        if(tmp == null) {
            model.addAttribute("error", "Product create error");
            return "pageError";
        }
        return "redirect:/products";
    }

    @GetMapping("/edit-products")
    public String productEdit(@RequestParam(name = "product_id", required = true) long product_id, Model model) {
        Products products = productsServices.getProductById(product_id);
        model.addAttribute("product", products);
        return "EditProduct";
    }

    @PostMapping("/save-edit-product")
    public String editProduct(@RequestParam(name = "product_id") long product_id,
                              @RequestParam(name = "product_name") String product_name,
                              @RequestParam(name = "type") String type,
                              @RequestParam(name = "quantity") int quantity,
                              @RequestParam(name = "measure") String measure,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "expiration_date_from", required = false) java.util.Date expiration_date_from,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "expiration_date_to", required = false) java.util.Date expiration_date_to,
                              @RequestParam(name = "place_id", defaultValue = "-1") long place_id,
                              Model model) {
        Products old_product = productsServices.getProductById(product_id);
        Register_place register_place;
        if (place_id > 0) {
            register_place = register_placeServices.getRegPlaceById(place_id);
            if (register_place == null) {
                model.addAttribute("error", "Not exists place");
                return "pageError";
            }
        } else {
            model.addAttribute("error", "Product save error");
            return "pageError";
        }
        Date sql_expiration_date_from = new Date(expiration_date_from.getTime());
        Date sql_expiration_date_to = new Date(expiration_date_to.getTime());

        old_product.setProduct_name(product_name);
        old_product.setType(type);
        old_product.setQuantity(quantity);
        old_product.setMeasure(measure);
        old_product.setExpiration_date_from(sql_expiration_date_from);
        old_product.setExpiration_date_to(sql_expiration_date_to);
        old_product.setPlace_id(register_place);

        productsServices.updateProduct(old_product);

        Products tmp = productsServices.getProductById(old_product.getProduct_id());
        if(tmp == null) {
            model.addAttribute("error", "Product create error");
            return "pageError";
        }
        return "redirect:/products";
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam(name = "product_id", required = true) long product_id,
                                @RequestParam(name = "place_id", required = true) long place_id,
                                Model model) {
        Products products = productsServices.getProductById(product_id);
        productsServices.deleteProduct(products);
        Register_place register_place = register_placeServices.getRegPlaceById(place_id);
        register_place.setFree("да");
        register_placeServices.updateRegisterPlace(register_place);
        return "redirect:/products";
    }

    @GetMapping("/filter-product")
    public String filtration(Model model) {
        return "FiltrationProduct";
    }

    @PostMapping("/filtered")
    public String filterProduct(@RequestParam(name = "type", required = false) String type,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_after", required = false) java.util.Date date_after,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "date_before", required = false) java.util.Date date_before,
                                Model model) {

        List<Products> prod_type = productsServices.getAllProduct();
        List<Products> prod_after = productsServices.getAllProduct();
        List<Products> prod_before = productsServices.getAllProduct();

        if(!Objects.equals(type, "")) {
            prod_type = productsServices.getProductType(type);
        }
        if(date_after != null) {
            Date sql_date_after = new Date(date_after.getTime());
            prod_after = productsServices.getProductByDateAfter(sql_date_after);
        }
        if(date_before != null) {
            Date sql_date_before = new Date(date_before.getTime());
            prod_before = productsServices.getProductByDateBefore(sql_date_before);
        }

        prod_type.retainAll(prod_after);
        prod_type.retainAll(prod_before);

        model.addAttribute("filter_product", prod_type);

        return "UpdateProducts";
    }
}
