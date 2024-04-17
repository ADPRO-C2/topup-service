package com.example.secondtreasurebe.factory;

import com.example.secondtreasurebe.model.CardPaymentDetails;
import com.example.secondtreasurebe.model.EWalletPaymentDetails;
import com.example.secondtreasurebe.model.PaymentDetails;
import org.springframework.stereotype.Component;

@Component
public class SimplePaymentDetailsFactory implements PaymentDetailsFactory {
    @Override
    public PaymentDetails createCardPaymentDetails(String cardNumber, String cvc, String expiryDate) {
        CardPaymentDetails card = new CardPaymentDetails();
        card.setCardNumber(cardNumber);
        card.setCvc(cvc);
        card.setExpiryDate(expiryDate);
        return card;
    }

    @Override
    public PaymentDetails createEWalletPaymentDetails(String phoneNumber) {
        EWalletPaymentDetails eWallet = new EWalletPaymentDetails();
        eWallet.setPhoneNumber(phoneNumber);
        return eWallet;
    }
}
