package com.example.marketapp.repository;

import com.example.marketapp.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * "
            + "FROM users_products "
            + "LEFT JOIN users "
            + "ON users_products.user_id = users.id "
            + "WHERE products_id = ?", nativeQuery = true)
    List<User> getAllUsersByProductId(Long productId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"products"})
    List<User> findAll();
}
