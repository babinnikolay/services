package ru.hukola.services.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    public ResponseEntity<Collection<Order>> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                     @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid Order order) throws Exception {
        return ResponseEntity.ok(orderService.create(order));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Order> save(@PathVariable @NotNull UUID uuid, @RequestBody @Valid Order order) {
        return ResponseEntity.ok(orderService.create(uuid, order));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull UUID uuid) {
        orderService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
