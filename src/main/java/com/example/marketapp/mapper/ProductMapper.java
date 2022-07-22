package com.example.marketapp.mapper;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }
}
