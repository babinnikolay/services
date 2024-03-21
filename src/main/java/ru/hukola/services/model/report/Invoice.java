package ru.hukola.services.model.report;

import lombok.Getter;
import lombok.Setter;
import ru.hukola.services.model.Order;

import java.util.Collection;
import java.util.Date;

/**
 * @author Babin Nikolay
 */
@Getter @Setter
public class Invoice{
    private Date from;
    private Date to;
    private Collection<Order> orders;
    private double amount;
}
