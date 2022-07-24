package com.example.marketapp.controller;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Create new product")
    public ProductResponseDto createProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product by id")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @Valid @RequestBody ProductRequestDto
                                                    productRequestDto) {
        return productService.updateProductById(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete product by id")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Product by id " + id + " was deleted";
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Get all products by user id")
    public List<ProductResponseDto> getAllProductsByUserId(@PathVariable Long id) {
        return productService.getAllProductsByUserId(id);
    }
}
