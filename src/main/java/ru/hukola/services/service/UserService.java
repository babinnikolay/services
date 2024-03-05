package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.User;
import ru.hukola.services.repository.UserRepository;

import java.util.Collection;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
