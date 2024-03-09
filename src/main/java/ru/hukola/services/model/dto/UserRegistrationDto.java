package ru.hukola.services.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Babin Nikolay
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
}
