package ru.hukola.services.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
