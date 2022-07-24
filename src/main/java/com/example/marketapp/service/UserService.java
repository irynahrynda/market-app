package com.example.marketapp.service;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);

    void deleteUserById(Long id);

    UserResponseDto buyProduct(Long userId, Long productId);

    List<UserResponseDto> getAllUsersByProductId(Long id);
}
