package com.example.marketapp.mapper;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setAmountOfMoney(userRequestDto.getAmountOfMoney());
        return user;
    }

    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setAmountOfMoney(user.getAmountOfMoney());
        userResponseDto.setProductIds(user.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return userResponseDto;
    }
}
