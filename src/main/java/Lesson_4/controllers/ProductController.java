package Lesson_4.controllers;

import Lesson_4.model.Product;
import Lesson_4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8989/app
//    @GetMapping
//    public String showAllProducts(Model model) {
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        return "all_products";
//    }

    @GetMapping
    public String showAllProductsPageByPage(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        List<Product> products = productService.findByPage(pageNumber - 1, 5).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String showProductForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productService.saveOrUpdateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct) {
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products";
    }

    @GetMapping("/info_by_title")
    @ResponseBody
    public Product infoByName(@RequestParam String title) {
        return productService.findByTitle(title);
    }

    @GetMapping("/min_price")
    public String allMinProducts(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        List<Product> products = productService.findProductsWithMinCost(pageNumber - 1, 5).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/max_price")
    public String allMaxProducts(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        List<Product> products = productService.findProductsWithMaxCost(pageNumber - 1, 5).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/minmax_price")
    public String allMinMaxProducts(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        List<Product> products = productService.findProductsWithMinMaxCost(pageNumber - 1, 5).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }
}
