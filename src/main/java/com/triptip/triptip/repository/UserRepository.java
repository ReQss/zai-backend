package com.triptip.triptip.repository;

import com.triptip.triptip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String name);
    User findById(int id);
    List<User>findAll();
}
