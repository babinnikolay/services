package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Client;
import ru.hukola.services.repository.ClientRepository;

import java.util.Collection;

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
}
