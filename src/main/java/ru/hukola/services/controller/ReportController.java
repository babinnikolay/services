package ru.hukola.services.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hukola.services.model.report.Invoice;
import ru.hukola.services.model.report.TotalReportsData;
import ru.hukola.services.service.ReportService;

import java.util.Date;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<TotalReportsData> getTotalReportsData() {
        return ResponseEntity.ok(reportService.getTotalReportsData());
    }

    @GetMapping("invoice")
    public ResponseEntity<Invoice> getInvoice(@RequestParam boolean allPeriods,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
                                              @RequestParam UUID clientId) {


        return ResponseEntity.ok(reportService.getInvoice(allPeriods, from, to, clientId));

    }


}
