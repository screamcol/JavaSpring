package Lesson_2.service;

import Lesson_2.model.Product;
import Lesson_2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProduct();
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.saveOrUpdateProduct(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}
