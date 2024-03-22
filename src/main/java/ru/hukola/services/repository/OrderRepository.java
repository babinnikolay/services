package ru.hukola.services.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.Order;
import ru.hukola.services.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, UUID>, JpaRepository<Order, UUID> {
    Page<Order> findAllByUser(User user, Pageable pageable);

    Optional<Order> findByUuidAndUser(UUID id, User user);

    Collection<Order> findAllByClientAndDateBetweenAndPaidAndUserOrderByDate(Client client, Date from, Date to, boolean paid, User user);

    Collection<Order> findAllByClientAndPaidAndUserOrderByDate(Client client, boolean paid, User user);

    Collection<Order> findAllByPaidAndUser(boolean paid, User user);
}
