package com.example.annihilator.services;

public interface EmailService {

    String sendEmail(String subject, String content);

    String checkIp();
}
