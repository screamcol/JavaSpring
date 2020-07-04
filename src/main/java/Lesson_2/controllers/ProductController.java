package Lesson_2.controllers;

import Lesson_2.model.Product;
import Lesson_2.service.ProductService;
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
    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String showProductForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Product product) {
//        Product product = new Product(productId, productName, productCost);
        productService.saveOrUpdateProduct(product);
        return "redirect:/all_products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_student_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Product modifiedStudent) {
        productService.saveOrUpdateProduct(modifiedStudent);
        return "redirect:/students/";
    }
}
