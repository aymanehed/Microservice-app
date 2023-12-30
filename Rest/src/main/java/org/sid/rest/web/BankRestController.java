package org.sid.rest.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/bank")
public class BankRestController {
    @GetMapping(path = "/convert")
    public double convert(@RequestParam(name = "amount") double amount, @RequestParam(name = "fromCurrency") String fromCurrency, @RequestParam(name = "toCurrency") String toCurrency) {
        double result = 0;
        if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            result = amount * 1.17;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            result = amount / 1.17;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("MAD")) {
            result = amount * 10.8;
        } else if (fromCurrency.equals("MAD") && toCurrency.equals("EUR")) {
            result = amount / 10.8;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("MAD")) {
            result = amount * 9.2;
        } else if (fromCurrency.equals("MAD") && toCurrency.equals("USD")) {
            result = amount / 9.2;
        }
        return result;
    }
}