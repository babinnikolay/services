package ru.hukola.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hukola.services.model.Client;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, UUID>, JpaRepository<Client, UUID> {
}
