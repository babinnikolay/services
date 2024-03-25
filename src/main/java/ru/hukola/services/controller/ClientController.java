package ru.hukola.services.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hukola.services.model.Client;
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
    public ResponseEntity<Collection<Client>> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(clientService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid Client client) {
        return ResponseEntity.ok(clientService.create(client));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Client> save(@PathVariable @NotNull UUID uuid, @RequestBody @Valid Client client) {
        return ResponseEntity.ok(clientService.save(uuid, client));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull UUID uuid) {
        clientService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
