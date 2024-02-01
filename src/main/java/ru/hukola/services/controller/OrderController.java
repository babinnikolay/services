package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hukola.services.model.Order;
import ru.hukola.services.service.OrderService;

import java.util.Collection;
import java.util.UUID;

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

    @GetMapping("{uuid}")
    public ResponseEntity<Order> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(orderService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Order> save(@PathVariable UUID uuid, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.create(uuid, order));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        orderService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
