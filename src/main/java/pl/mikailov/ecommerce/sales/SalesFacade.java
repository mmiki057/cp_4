package pl.mikailov.ecommerce.sales;

public class SalesFacade {
    public void addToCart(String productId) {}

    public void acceptOffer(AcceprOfferCommand acceptOffer){}

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public void makeReservationPaid(String reservationId) {}
}
