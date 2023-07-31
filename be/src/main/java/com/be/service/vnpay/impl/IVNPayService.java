package com.be.service.vnpay.impl;
import com.be.dto.payment.PaymentSendEmailDTO;


public interface IVNPayService {
    void sendEmail(PaymentSendEmailDTO paymentSendEmailDTO);
}
