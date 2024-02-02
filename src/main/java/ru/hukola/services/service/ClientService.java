package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.Order;
import ru.hukola.services.repository.ClientRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(UUID uuid) {
        return clientRepository.findById(uuid).orElseThrow();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client save(UUID uuid, Client client) {
        Client savedClient = clientRepository.findById(uuid).orElseThrow();
        savedClient.setName(client.getName());
        clientRepository.save(client);
        return savedClient;
    }

    public void delete(UUID uuid) {
        clientRepository.deleteById(uuid);
    }
}
