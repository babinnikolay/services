package ru.hukola.services.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, UUID>, JpaRepository<Client, UUID> {
    Page<Client> findAllByUser(User user, Pageable pageable);

    Optional<Client> findByUuidAndUser(UUID uuid, User user);
}
