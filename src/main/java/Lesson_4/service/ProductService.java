package Lesson_4.service;

import Lesson_4.exceptions.ProductNotFoundException;
import Lesson_4.model.Product;
import Lesson_4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " wasn't found"));
    }

    public Product findByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public Page<Product> findProductsWithMinCost(int pageNumber, int pageSize) {
        return productRepository.findAllByCostEquals(productRepository.findMinimum(), PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> findProductsWithMaxCost(int pageNumber, int pageSize) {
        return productRepository.findAllByCostEquals(productRepository.findMaximum(), PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> findProductsWithMinMaxCost(int pageNumber, int pageSize) {
        return productRepository.findAllByCostEqualsOrCostEquals(productRepository.findMinimum(), productRepository.findMaximum(), PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> findByPage(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
