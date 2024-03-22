package ru.hukola.services.service;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Order;
import ru.hukola.services.repository.OrderRepository;
import ru.hukola.services.model.User;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserService userService;

    public Collection<Order> findAll(int offset, int size) {
        User user = userService.getSecurityUser();
        Page<Order> page = orderRepository.findAllByUser(user, PageRequest.of(offset, size, Sort.by("date")));
        return page.stream().toList();
    }

    public Order findById(UUID uuid) {
        User user = userService.getSecurityUser();
        return orderRepository.findByUuidAndUser(uuid, user).orElseThrow();
    }

    public Order create(Order order) throws Exception {
        if (order.getClient().getUuid() == null) {
            throw new ValidationException("Client must be selected");
        }
        User user = userService.getSecurityUser();
        order.setUser(user);

        return orderRepository.save(order);
    }

    public Order create(UUID uuid, Order order) {
        User user = userService.getSecurityUser();
        Order savedOrder = orderRepository.findById(uuid).orElseThrow();
        savedOrder.setAmount(order.getAmount());
        savedOrder.setPaid(order.isPaid());
        savedOrder.setClient(order.getClient());
        savedOrder.setDescription(order.getDescription());
        savedOrder.setDate(order.getDate());
        savedOrder.setUser(user);
        orderRepository.save(savedOrder);
        return savedOrder;
    }

    public void delete(UUID uuid) {
        if (findById(uuid) != null)
            orderRepository.deleteById(uuid);
    }
}
