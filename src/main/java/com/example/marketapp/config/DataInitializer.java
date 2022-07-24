package com.example.marketapp.config;

import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.repository.UserRepository;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DataInitializer(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void inject() {
        Product apple = new Product();
        apple.setName("Apple");
        apple.setPrice(BigDecimal.valueOf(22));
        productRepository.save(apple);

        Product banana = new Product();
        banana.setName("Banana");
        banana.setPrice(BigDecimal.valueOf(46));
        productRepository.save(banana);

        Product peach = new Product();
        peach.setName("Peach");
        peach.setPrice(BigDecimal.valueOf(234));
        productRepository.save(peach);

        User userBob = new User();
        userBob.setFirstName("Bob");
        userBob.setLastName("Jonson");
        userBob.setAmountOfMoney(BigDecimal.valueOf(232));
        userRepository.save(userBob);

        User userAlice = new User();
        userAlice.setFirstName("Alice");
        userAlice.setLastName("Robson");
        userAlice.setAmountOfMoney(BigDecimal.valueOf(345));
        userRepository.save(userAlice);

        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Pitt");
        userJohn.setAmountOfMoney(BigDecimal.valueOf(543));
        userRepository.save(userJohn);
    }
}
