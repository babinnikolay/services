package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.User;
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
    private final UserService userService;

    public Collection<Client> findAll(int offset, int size) {
        User user = userService.getSecurityUser();
        Page<Client> page = clientRepository.findAllByUser(user, PageRequest.of(offset, size, Sort.by("name")));
        return page.stream().toList();
    }

    public Client findById(UUID uuid) {
        User user = userService.getSecurityUser();
        return clientRepository.findByUuidAndUser(uuid, user).orElseThrow();
    }

    public Client create(Client client) {
        client.setUser(userService.getSecurityUser());
        return clientRepository.save(client);
    }

    public Client save(UUID uuid, Client client) {
        Client savedClient = clientRepository.findById(uuid).orElseThrow();
        savedClient.setUser(userService.getSecurityUser());
        savedClient.setName(client.getName());
        clientRepository.save(savedClient);
        return savedClient;
    }

    public void delete(UUID uuid) {
        if (findById(uuid) != null)
            clientRepository.deleteById(uuid);
    }
}
