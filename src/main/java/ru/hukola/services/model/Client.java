package ru.hukola.services.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Entity(name="clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    @ManyToOne
    private User user;
}
