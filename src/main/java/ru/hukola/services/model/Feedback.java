package ru.hukola.services.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String title;
    @NotBlank
    private String description;
    @ManyToOne
    private User user;
}
