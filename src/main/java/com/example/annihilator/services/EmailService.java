package com.example.annihilator.services;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void beginDestruction(String subject, String content) throws MessagingException, IOException, InterruptedException;

    void notifyAboutIpChange(String currentIp);
}
