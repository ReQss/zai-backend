package com.triptip.triptip.controller;

import com.triptip.triptip.model.Feedback;
import com.triptip.triptip.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class FeedbackController {
    @Autowired
    FeedbackRepository feedbackRepository;
    @GetMapping("/admin/feedback")
    public List<Feedback> getFeedback(){
        return feedbackRepository.findAll();
    }
    @DeleteMapping("/admin/removeFeedback/{id}")
    public void removeFeedback(@PathVariable int id){
        Feedback feedback = feedbackRepository.findById(id);
        if(feedback==null)System.out.println("Not found");
        else
            feedbackRepository.delete(feedback);
    }
    @PostMapping("/feedback")
    public void addFeedback(@RequestBody Feedback feedback){
        feedbackRepository.save(feedback);
    }
}
