package pl.mikailov.ecommerce.Sales;


import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void checkoutHappyPath() {
        String productId = thereIsProduct("1234", BigDecimal.valueOf(11));
        //curl -X POST http://localhost:9999/api/add-product/1234
        var productEndpoint = String.format("/api/add-product/%s", productId);
        var toBeCalledURL = getBaseURL(productEndpoint);

        http.postForEntity(toBeCalledURL, null, null);
        http.postForEntity(toBeCalledURL, null, null);

        var currentOfferEndpoint = "/api/current-offer";
        var toBeCalledURLOffer = getBaseURL(currentOfferEndpoint);
        http.getForEntity(toBeCalledURLOffer, Offer.class);

    }

    private String getBaseURL(String productEndpoint) {
        return String.format("http://localhost:%s%s", port, productEndpoint);
    }
}
