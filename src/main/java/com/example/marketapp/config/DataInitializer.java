package com.example.marketapp.config;

import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ProductService productService;
    private final UserService userService;

    public DataInitializer(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Product apple = new Product();
        apple.setName("Apple");
        apple.setPrice(BigDecimal.valueOf(22));
        productService.createProduct(apple);

        Product banana = new Product();
        banana.setName("Banana");
        banana.setPrice(BigDecimal.valueOf(46));
        productService.createProduct(banana);

        Product peach = new Product();
        peach.setName("Peach");
        peach.setPrice(BigDecimal.valueOf(234));
        productService.createProduct(peach);

        User userBob = new User();
        userBob.setFirstName("Bob");
        userBob.setLastName("Jonson");
        userBob.setAmountOfMoney(BigDecimal.valueOf(232));
        userService.createUser(userBob);

        User userAlice = new User();
        userAlice.setFirstName("Alice");
        userAlice.setLastName("Robson");
        userAlice.setAmountOfMoney(BigDecimal.valueOf(345));
        userService.createUser(userAlice);

        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Pitt");
        userJohn.setAmountOfMoney(BigDecimal.valueOf(543));
        userService.createUser(userJohn);
    }
}
