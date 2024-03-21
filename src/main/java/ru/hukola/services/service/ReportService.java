package ru.hukola.services.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hukola.services.model.Client;
import ru.hukola.services.model.Order;
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
    private final OrderRepository orderRepository;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public TotalReportsData getTotalReportsData() {
        Collection<Order> orders = orderRepository.findAllByPaid(false);

        TotalReportsData data = new TotalReportsData();
        data.setTotalUnpaidOrders(orders.size());
        data.setTotalUnpaidAmount(orders.stream().mapToDouble(Order::getAmount).sum());
        data.setTotalUnpaidClients(orders.stream().map(Order::getClient).collect(Collectors.toSet()).size());
        return data;
    }

    public Invoice getInvoice(boolean forAllPeriods, Date from, Date to, UUID clientId) {
        Client client = clientService.findById(clientId);
        Collection<Order> orders;
        Invoice invoice = new Invoice();

        if (forAllPeriods) {
            orders = orderRepository.findAllByClientAndPaidOrderByDate(client, false);
            Date min = orders.stream().map(Order::getDate).min(Date::compareTo).orElse(new Date());
            Date max = orders.stream().map(Order::getDate).max(Date::compareTo).orElse(new Date());
            invoice.setFrom(min);
            invoice.setTo(max);
        } else {
            orders = orderRepository.findAllByClientAndDateBetweenAndPaidOrderByDate(client, from, to, false);
            invoice.setFrom(from);
            invoice.setTo(to);
        }
        invoice.setOrders(orders);
        invoice.setAmount(orders.stream().mapToDouble(Order::getAmount).sum());
        return invoice;
    }
}
