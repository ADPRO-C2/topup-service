package com.example.secondtreasurebe.strategy;
import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.EWalletPaymentDetails;
import com.example.secondtreasurebe.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service("eWalletPaymentStrategy")
public class EWalletPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentDetails processPayment(PaymentMethodRequest request) {
        EWalletPaymentDetails eWalletDetails = new EWalletPaymentDetails();
        eWalletDetails.setPhoneNumber(request.getPhoneNumber());
        return eWalletDetails;
    }
}
