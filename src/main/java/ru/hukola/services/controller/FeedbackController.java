package ru.hukola.services.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hukola.services.service.FeedbackService;
import ru.hukola.services.model.Feedback;

/**
 * @author Babin Nikolay
 */
@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Void> newFeedback(@RequestBody @Valid Feedback feedback) {
        feedbackService.newFeedback(feedback);
        return ResponseEntity.ok(null);
    }
}
