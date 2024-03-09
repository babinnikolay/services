package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.User;
import ru.hukola.services.model.dto.UserRegistrationDto;
import ru.hukola.services.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return findUserByName(authentication.getName());
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User createUser(UserRegistrationDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUuid(UUID.randomUUID());
        user.setPassword(dto.getPassword());
        return this.userRepository.save(user);
    }
}
