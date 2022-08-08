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
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (productRequestDto.getName() != null) {
            productToUpdate.setName(productRequestDto.getName());
        }
        if (productRequestDto.getPrice() != null) {
            productToUpdate.setPrice(productRequestDto.getPrice());
        }
        return productMapper.mapToDto(productRepository.save(productToUpdate));
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        Product deleteProduct = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get product by id: " + id));
        List<User> usersByProductId = userRepository.getAllUsersByProductId(id);
        for (int i = 0; i < usersByProductId.size(); i++) {
            Set<Product> products = usersByProductId.get(i).getProducts();
            products.remove(deleteProduct);
            usersByProductId.get(i).setProducts(products);
            User userToUpdate = userRepository.save(usersByProductId.get(i));
            usersByProductId.set(i, userToUpdate);
        }
        productRepository.deleteById(id);
        productMapper.mapToDto(deleteProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByUserId(Long id) {
        List<ProductResponseDto> products = productRepository.getAllProductsByUserId(id).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return products;
    }
}
