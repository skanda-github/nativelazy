package com.example.nativelazy.repository;

import com.example.nativelazy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Native SQL query
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);
}
