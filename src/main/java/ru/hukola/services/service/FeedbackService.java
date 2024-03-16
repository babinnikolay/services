package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Feedback;
import ru.hukola.services.model.User;
import ru.hukola.services.repository.FeedbackRepository;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public void newFeedback(Feedback feedback) {
        User user = userService.getSecurityUser();
        feedback.setUser(user);
        this.feedbackRepository.save(feedback);
    }
}
