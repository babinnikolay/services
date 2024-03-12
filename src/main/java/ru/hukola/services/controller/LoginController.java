package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.hukola.services.model.User;
import ru.hukola.services.model.dto.NewUserCredentialDto;
import ru.hukola.services.model.dto.UserRegistrationDto;
import ru.hukola.services.service.UserService;

/**
 * @author Babin Nikolay
 */
@RestController
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<User> getUserDetails(Authentication authentication) {
        User user = userService.findUserByName(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/change/email")
    public ResponseEntity<NewUserCredentialDto> changeEmail(@RequestBody NewUserCredentialDto dto) {
        return ResponseEntity.ok(userService.changeEmail(dto));
    }

    @PutMapping("/change/password")
    public ResponseEntity<NewUserCredentialDto> changePassword(@RequestBody NewUserCredentialDto dto) {
        return ResponseEntity.ok(userService.changePassword(dto));
    }
}
