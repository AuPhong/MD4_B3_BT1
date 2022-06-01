package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    ProductServiceImpl productService = new ProductServiceImpl();

    @GetMapping("")
    public String showAll(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirectAttributes) {
        int id = (int) Math.round(Math.random() * 1000);
        product.setId(id);
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Successful!");
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/edit";
    }

    @PostMapping("update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
//        Product product = productService.findById(id);
        productService.remove(id);
        return "redirect:/product";
    }

    @GetMapping("search")
    public String search( String name, Model model) {
        List<Product> productList = new ArrayList<>();
        if (name!=null){
            productList = productService.searchByName(name);
            model.addAttribute("productList",productList);
        } else {
            productList = productService.findAll();
            model.addAttribute("productList",productList);
        }
        return "/index";
    }
}
