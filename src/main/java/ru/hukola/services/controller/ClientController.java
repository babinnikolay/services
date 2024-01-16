package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hukola.services.model.Client;
import ru.hukola.services.service.ClientService;

import java.util.Collection;

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
}
