package com.example.marketapp.service;

import com.example.marketapp.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void deleteProductById(Long id);
}
