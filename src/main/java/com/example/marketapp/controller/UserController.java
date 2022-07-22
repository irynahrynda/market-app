package com.example.marketapp.controller;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.mapper.ProductMapper;
import com.example.marketapp.mapper.UserMapper;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public UserController(UserService userService, ProductService productService,
                          UserMapper userMapper, ProductMapper productMapper) {
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new user")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userMapper.mapToDto(userService.createUser(userMapper.mapToModel(userRequestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userMapper.mapToDto(userService.getUserById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                          @Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.mapToModel(userRequestDto);
        user.setId(id);
        return userMapper.mapToDto(userService.createUser(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User by id " + id + " was deleted";
    }

    @PutMapping("/{id}/buy")
    @ApiOperation(value = "Update user after purchase product")
    public String buyProduct(@PathVariable Long id, @RequestParam Long productId) {
        userMapper.mapToDto(userService.buyProduct(id, productId));
        return "Successful purchase!";
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Get all users by product id")
    public List<UserResponseDto> getAllUsersByProductId(@PathVariable Long id) {
        List<UserResponseDto> users = userService.getAllUsersByProductId(id).stream()
                .map(e -> userMapper.mapToDto(e))
                .collect(Collectors.toList());
        return users;
    }
}
