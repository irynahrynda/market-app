package com.example.marketapp.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
}
