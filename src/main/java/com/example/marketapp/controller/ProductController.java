package com.example.marketapp.controller;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.mapper.ProductMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UserService userService;

    public ProductController(ProductService productService, ProductMapper productMapper,
                             UserService userService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.mapToDto(productService.createProduct(
                productMapper.mapToModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapper.mapToDto(productService.getProductById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product by id")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productMapper.mapToDto(productService.createProduct(product));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete product by id")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Product by id " + id + " was deleted";
    }

    @GetMapping("/users/{id}")
    public List<ProductResponseDto> getAllProductsByUserId(@PathVariable Long id) {
        User userById = userService.getUserById(id);
        List<ProductResponseDto> products = userById.getProducts().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return products;
    }
}
