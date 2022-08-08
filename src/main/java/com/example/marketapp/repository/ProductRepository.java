package com.example.marketapp.repository;

import com.example.marketapp.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * "
            + "FROM users_products "
            + "LEFT JOIN products "
            + "ON users_products.products_id = products.id "
            + "WHERE user_id = ?", nativeQuery = true)
    List<Product> getAllProductsByUserId(Long id);
}
