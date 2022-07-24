package com.example.marketapp.controller;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Create new user")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                          @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUserById(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User by id " + id + " was deleted";
    }

    @PutMapping("/{id}/buy")
    @ApiOperation(value = "Update user after buying a product")
    public String buyProduct(@PathVariable Long id, @RequestParam Long productId) {
        userService.buyProduct(id, productId);
        return "Successful purchase!";
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Get all users by product id")
    public List<UserResponseDto> getAllUsersByProductId(@PathVariable Long id) {
        return userService.getAllUsersByProductId(id);
    }
}
