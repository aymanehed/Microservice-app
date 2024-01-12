package org.sid.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GraphQlApplicationTests {
    @Autowired
    BankGraphQLController bankGraphQLResolver;
    @Test
    public void testConvert() {
            // Test conversion from EUR to USD
            assertEquals(117.0, bankGraphQLResolver.convert(100.0, "EUR", "USD"), 0.01);


            // Test conversion from USD to MAD
            assertEquals(920.0, bankGraphQLResolver.convert(100.0, "USD", "MAD"), 0.01);

            // Test conversion from MAD to USD
            assertEquals(10.87, bankGraphQLResolver.convert(100.0, "MAD", "USD"), 0.01);
        }


    @Test
    void contextLoads() {
    }

}
