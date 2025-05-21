package pl.mikailov.ecommerce.Sales;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mikailov.ecommerce.sales.Offer;
import pl.mikailov.ecommerce.sales.SalesFacade;
import pl.mikailov.productcatalog.ArrayListProductStorage;
import pl.mikailov.productcatalog.ProductCatalog;

import java.math.BigDecimal;

public class SalesTest {

    ProductCatalog catalog;

    @BeforeEach
    void setup(){
        catalog = new ProductCatalog(new ArrayListProductStorage());

    }

    @Test
    void itAllowsToCollectProductsByCustomersSeparately(){
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customer1 = thereIsCustomer("Kuba");
        String customer2 = thereIsCustomer("Misza");
        String productId = thereIsProduct("Product X", BigDecimal.valueOf(10));

        //Act

        sales.addToCart(customer1, productId);
        sales.addToCart(customer2, productId);
        sales.addToCart(customer2, productId);

        Offer offer1 = sales.getCurrentOffer(customer1);
        Offer offer2 = sales.getCurrentOffer(customer1);

        assertEquals(BigDecimal.valueOf(10), offer1.getTotal);
        assertEquals(BigDecimal.valueOf(10), offer1.getTotal);}

    private String thereIsProduct(String productX, BigDecimal bigDecimal) {
        return null;
    }

    private SalesFacade thereIsSalesModuleUnderTest() {

        SalesFacade o111;
        return o111;
    }

    @Test
    void itShowsEmptyOffer(){
        SalesFacade sales = thereIsSalesModule();
        String customerId = thereIsCustomer();

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsCustomer(String customerName) {
        return String.format("customer__%s");
        }
    }
}
