package ru.hukola.services.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @OneToOne
    private Client client;
    private String description;
    private float amount;
}
