package com.example.marketapp.service.impl;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.mapper.UserMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.repository.UserRepository;
import com.example.marketapp.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.productRepository = productRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return userMapper.mapToDto(userRepository.save(userMapper.mapToModel(userRequestDto)));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userMapper.mapToDto(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get user by id " + id)));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get user by id: " + id));
        userToUpdate.setFirstName(userRequestDto.getFirstName());
        userToUpdate.setLastName(userRequestDto.getLastName());
        userToUpdate.setAmountOfMoney(userRequestDto.getAmountOfMoney());
        return userMapper.mapToDto(userRepository.save(userToUpdate));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto buyProduct(Long userId, Long productId) {
        User user = userRepository.getById(userId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Can't get product by id: " + productId));
        if (product.getPrice().compareTo(user.getAmountOfMoney()) > 0) {
            throw new RuntimeException("Insufficient funds to buy products");
        } else {
            user.setAmountOfMoney(user.getAmountOfMoney().subtract(product.getPrice()));
            Set<Product> products = user.getProducts();
            products.add(product);
            user.setProducts(products);
        }
        return userMapper.mapToDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> getAllUsersByProductId(Long id) {
        List<UserResponseDto> users = userRepository.findAll().stream()
                .filter(e -> e.getProducts().contains(productRepository.getById(id)))
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
        return users;
    }
}
