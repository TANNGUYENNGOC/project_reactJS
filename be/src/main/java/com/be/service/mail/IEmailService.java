package com.be.service.mail;


import com.be.model.user.User;

public interface IEmailService {
    void sendResetPasswordEmail(String email, String otp);
    boolean validateOtp(String otpCode, String email);
    String generateOtp(User user);
}
