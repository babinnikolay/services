package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.Order;
import ru.hukola.services.model.User;
import ru.hukola.services.model.report.Invoice;
import ru.hukola.services.model.report.TotalReportsData;
import ru.hukola.services.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Babin Nikolay
 */
@Service
@AllArgsConstructor
public class ReportService {
    private final ClientService clientService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public TotalReportsData getTotalReportsData() {
        User user = userService.getSecurityUser();
        Collection<Order> orders = orderRepository.findAllByPaidAndUser(false, user);

        TotalReportsData data = new TotalReportsData();
        data.setTotalUnpaidOrders(orders.size());
        data.setTotalUnpaidAmount(orders.stream().mapToDouble(Order::getAmount).sum());
        data.setTotalUnpaidClients(orders.stream().map(Order::getClient).collect(Collectors.toSet()).size());
        return data;
    }

    public Invoice getInvoice(boolean forAllPeriods, Date from, Date to, UUID clientId) {
        User user = userService.getSecurityUser();
        Client client = clientService.findById(clientId);
        Collection<Order> orders;
        Invoice invoice = new Invoice();

        if (forAllPeriods) {
            orders = orderRepository.findAllByClientAndPaidAndUserOrderByDate(client, false, user);
            Date min = orders.stream().map(Order::getDate).min(Date::compareTo).orElse(new Date());
            Date max = orders.stream().map(Order::getDate).max(Date::compareTo).orElse(new Date());
            invoice.setFrom(min);
            invoice.setTo(max);
        } else {
            orders = orderRepository.findAllByClientAndDateBetweenAndPaidAndUserOrderByDate(client, from, to, false, user);
            invoice.setFrom(from);
            invoice.setTo(to);
        }
        invoice.setOrders(orders);
        invoice.setAmount(orders.stream().mapToDouble(Order::getAmount).sum());
        return invoice;
    }
}
