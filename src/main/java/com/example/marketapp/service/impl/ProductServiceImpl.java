package com.example.marketapp.service.impl;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.mapper.ProductMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.repository.UserRepository;
import com.example.marketapp.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productMapper.mapToDto(productRepository.save(
                productMapper.mapToModel(productRequestDto)));
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return productMapper.mapToDto(productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get product by id " + id)));
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get product by id: " + id));
        productToUpdate.setName(productRequestDto.getName());
        productToUpdate.setPrice(productRequestDto.getPrice());
        return productMapper.mapToDto(productRepository.save(productToUpdate));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByUserId(Long id) {
        User user = userRepository.getById(id);
        List<ProductResponseDto> products = user.getProducts().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return products;
    }
}
