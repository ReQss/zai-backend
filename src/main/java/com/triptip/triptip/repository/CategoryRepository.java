package com.triptip.triptip.repository;

import com.triptip.triptip.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int id);
}
