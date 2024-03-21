package ru.hukola.services.model.report;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Babin Nikolay
 */
@Getter @Setter
public class TotalReportsData {
    private double totalUnpaidAmount;
    private int totalUnpaidOrders;
    private int totalUnpaidClients;
}
