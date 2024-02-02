package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.Order;
import ru.hukola.services.service.ClientService;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Collection<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("{uuid}")
    public ResponseEntity<Client> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(clientService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.create(client));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Client> save(@PathVariable UUID uuid, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(uuid, client));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        clientService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
