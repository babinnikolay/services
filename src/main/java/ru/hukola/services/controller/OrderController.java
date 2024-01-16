package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hukola.services.model.Order;
import ru.hukola.services.service.OrderService;

import java.util.Collection;

/**
 * @author Babin Nikolay
 */
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Collection<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
