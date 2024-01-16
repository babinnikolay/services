package ru.hukola.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hukola.services.model.Order;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
