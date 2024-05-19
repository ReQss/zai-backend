package com.triptip.triptip.repository;

import com.triptip.triptip.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAll();
    Feedback findById(int id);
}
