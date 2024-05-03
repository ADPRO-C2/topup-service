package com.example.secondtreasurebe.strategy;
import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.CardPaymentDetails;
import com.example.secondtreasurebe.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service("cardPaymentStrategy")
public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentDetails processPayment(PaymentMethodRequest request) {
        CardPaymentDetails cardDetails = new CardPaymentDetails();
        cardDetails.setCardNumber(request.getCardNumber());
        cardDetails.setCvc(request.getCvc());
        cardDetails.setExpiryDate(request.getExpiryDate());
        return cardDetails;
    }
}
