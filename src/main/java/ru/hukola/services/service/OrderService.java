package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.web.FilterChainProxy;
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
    private ClientService clientService;

    private FilterChainProxy proxy;

    public Collection<Order> findAll(int offset, int size) {
        Page<Order> page = orderRepository.findAll(PageRequest.of(offset, size, Sort.by("date")));
        return page.stream().toList();
    }

    public Order findById(UUID uuid) {
        return orderRepository.findById(uuid).orElseThrow();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order create(UUID uuid, Order order) {
        Order savedOrder = orderRepository.findById(uuid).orElseThrow();
        savedOrder.setAmount(order.getAmount());
        savedOrder.setPaid(order.isPaid());
        savedOrder.setClient(order.getClient());
        savedOrder.setDescription(order.getDescription());
        savedOrder.setDate(order.getDate());
        orderRepository.save(savedOrder);
        return savedOrder;
    }

    public void delete(UUID uuid) {
        orderRepository.deleteById(uuid);
    }
}
