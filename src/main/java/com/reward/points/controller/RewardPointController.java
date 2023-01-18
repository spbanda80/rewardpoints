package com.reward.points.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.reward.points.model.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RewardPointController {

    private static final String TOTAL = "TOTAL";

    @PostMapping("/report")
    public Map getReportOfRewardPoints(@RequestBody(required = true) String transactionData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Transaction> transactionList = mapper.readValue(transactionData,
                new TypeReference<List<Transaction>>() {});
        LocalDate startDate1 = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endData1 = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        LocalDate startDate2 = LocalDate.now().minusMonths(2).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endData2 = LocalDate.now().minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        LocalDate startDate3 = LocalDate.now().minusMonths(3).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endData3 = LocalDate.now().minusMonths(3).with(TemporalAdjusters.lastDayOfMonth());

        Map reportMap = new HashMap();
        for (Transaction transaction : transactionList) {
            Object object = reportMap.get(transaction.getCustomerId());
            Map customerMap = null;
            if (object == null) {
                customerMap = getCustomerMap(startDate1, startDate2, startDate3);
            } else {
                customerMap = (Map) object;
            }

            if (updateReportList(transaction, startDate1, endData1, startDate2, endData2, startDate3, endData3, customerMap)) {
                customerMap.put(TOTAL, (int) customerMap.get(TOTAL) + transaction.getRewardPoints());
            }

            reportMap.put(transaction.getCustomerId(), customerMap);
        }

        return reportMap;
    }

    private Map getCustomerMap(LocalDate startDate1, LocalDate startDate2, LocalDate startDate3) {
        Map customerMap = new HashMap<>();
        customerMap.put(startDate1.getMonth(), 0);
        customerMap.put(startDate2.getMonth(), 0);
        customerMap.put(startDate3.getMonth(), 0);
        customerMap.put(TOTAL, 0);

        return customerMap;
    }

    public boolean updateReportList(Transaction transaction, LocalDate startDate1, LocalDate endData1,
                                    LocalDate startDate2, LocalDate endData2,
                                    LocalDate startDate3, LocalDate endData3,
                                    Map customerReportList) {
        if (!transaction.getTransactionDate().isBefore(startDate1) && transaction.getTransactionDate().isAfter(endData1)) {
            return updateRewardPoint(startDate1, transaction, customerReportList);
        }

        if (!transaction.getTransactionDate().isBefore(startDate2) && transaction.getTransactionDate().isAfter(endData2)) {
            return updateRewardPoint(startDate2, transaction, customerReportList);
        }

        if (!transaction.getTransactionDate().isBefore(startDate3) && transaction.getTransactionDate().isAfter(endData3)) {
            return updateRewardPoint(startDate3, transaction, customerReportList);
        }

        return false;
    }

    public boolean updateRewardPoint(LocalDate startDate, Transaction transaction, Map customerReportList) {
        customerReportList.put(startDate.getMonth(), (int) customerReportList.get(startDate.getMonth()) + transaction.getRewardPoints());
        return true;
    }
}
