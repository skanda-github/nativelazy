package com.example.nativelazy.controller;

import com.example.nativelazy.entity.*;
import com.example.nativelazy.repository.*;
import com.example.nativelazy.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;
    private UserService userService;

    public DemoController(UserRepository userRepo, OrderRepository orderRepo, UserService userService) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepo.findUserByEmail(email);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrdersForUser(@PathVariable Long userId) {
        return orderRepo.findOrdersByUserId(userId);
    }

    @GetMapping("/user-with-orders/{email}")
    public User getUserWithOrders(@PathVariable String email) {
        return userService.getUserWithOrdersByEmail(email);
    }

    // Seed sample data
    @PostMapping("/init")
    public String initData() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user = userRepo.save(user);

        Order order1 = new Order();
        order1.setItemName("Book");
        order1.setUser(user);

        Order order2 = new Order();
        order2.setItemName("Laptop");
        order2.setUser(user);

        orderRepo.saveAll(Arrays.asList(order1, order2));

        return "Data initialized.";
    }
}
