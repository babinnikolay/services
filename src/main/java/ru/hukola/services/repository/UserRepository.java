package ru.hukola.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hukola.services.model.User;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByNameIgnoreCase(String name);
}
