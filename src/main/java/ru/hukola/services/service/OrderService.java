package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Order;
import ru.hukola.services.repository.OrderRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID uuid) {
        return orderRepository.findById(uuid).orElseThrow();
    }
}
