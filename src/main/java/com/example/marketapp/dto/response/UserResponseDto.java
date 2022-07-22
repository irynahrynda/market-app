package com.example.marketapp.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal amountOfMoney;
    private List<Long> productIds;
}
