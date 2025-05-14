package com.example.nativelazy.service;

import com.example.nativelazy.entity.User;
import com.example.nativelazy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    @Transactional
    public User getUserWithOrdersByEmail(String email) {
        User user = userRepo.findUserByEmail(email);

        // Lazy loading triggered here inside the transaction
        user.getOrders().size(); // Forces loading of the orders list

        return user;
    }
}
