package com.example.marketapp.service;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto);

    void deleteProductById(Long id);

    List<ProductResponseDto> getAllProductsByUserId(Long id);
}
