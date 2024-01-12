package org.sid.graphql;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class BankGraphQLController {
    @QueryMapping
    public int convert(@Argument int amount,@Argument String fromCurrency,@Argument String toCurrency) {
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

        return (int) result;
    }
}
