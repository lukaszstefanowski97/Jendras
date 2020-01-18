package com.example.annihilator.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

    String sendEmail(String subject, String content) throws MessagingException;

    String checkIp();
}
