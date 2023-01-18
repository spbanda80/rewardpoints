package com.reward.points.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@RestController
public class TestDataController {


    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/testdata/{count}/{noofCustomers}")
    public String getTestData(@PathVariable(required = true) int count, @PathVariable(required = true) int noofCustomers) throws JsonProcessingException {


        LocalDate date =  LocalDate.now().minus(Period.ofDays((new Random().nextInt(80))));
        List<Map> transactionList = new ArrayList<>();

        for (int i=0; i<count; i++) {
            Map<String, Object> themap = new HashMap<>();

            themap.put("customerId", new Random().nextInt(noofCustomers) + 1);
            themap.put("transactionAmount", new Random().nextInt(100) + 50);
            themap.put("transactionDate", LocalDate.now().minus(Period.ofDays((new Random().nextInt(80)))).toString());

            transactionList.add(themap);
        }

        return objectMapper.writeValueAsString(transactionList);
    }
}
