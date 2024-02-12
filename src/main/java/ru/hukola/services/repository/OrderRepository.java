package ru.hukola.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hukola.services.model.Order;

import java.util.UUID;

/**
 * @author Babin Nikolay
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, UUID>, JpaRepository<Order, UUID> {
}
