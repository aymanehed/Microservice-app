package org.sid.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "convert")
    public double convert(double amount, String fromCurrency, String toCurrency) {
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
