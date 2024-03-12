package ru.hukola.services.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Babin Nikolay
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewUserCredentialDto {
    private String email;
    private String currentPassword;
    private String newPassword;
}
