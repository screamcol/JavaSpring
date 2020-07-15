package Lesson_4.repositories;

import Lesson_4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findOneByTitle(String title);
    Page<Product> findAllByCostEquals(int price, Pageable pageable);
    Page<Product> findAllByCostEqualsOrCostEquals(int minPrice, int maxPrice, Pageable pageable);

    @Query(value = "Select min(d.price) from products d", nativeQuery = true)
    Integer findMinimum();

    @Query(value = "Select max(d.price) from products d", nativeQuery = true)
    Integer findMaximum();
}
