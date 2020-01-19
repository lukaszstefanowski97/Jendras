package com.example.annihilator.services.impl;

import com.example.annihilator.services.EmailService;
import com.example.annihilator.utils.EnvConfig;
import com.example.annihilator.utils.Messages;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Data
@Slf4j
@ConfigurationProperties(prefix = "spring.mail")
public class EmailServiceImpl implements EmailService {

    private String host;
    private int port;
    private String username;
    private String password;
    private String receiver1;
    private String receiver2;
    private String receiver3;
    private String from;

    @Value("${addresses.lukasz}")
    private String lukasz;

    @Value("${addresses.jendras}")
    private String jendras;


    @Override
    public void beginDestruction(String subject, String content) throws MessagingException, IOException, InterruptedException {
        sendEmail(subject, content, getReceiver1());
        TimeUnit.SECONDS.sleep(5);
        sendEmail(subject, content, getReceiver2());
        TimeUnit.SECONDS.sleep(5);
        sendEmail(subject, content, getReceiver3());
    }

    void sendEmail(String subject, String content, String receiver) {
        JavaMailSenderImpl mailSender = createMailSender();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    private JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        return mailSender;
    }

    @Override
    public void notifyAboutIpChange(String currentIp) {

        JavaMailSenderImpl mailSender = createMailSender();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(lukasz, jendras);
        mailMessage.setSubject(Messages.IP_CHANGED_SUBJECT);
        mailMessage.setText(Messages.IP_CHANGED_MESSAGE + currentIp);

        mailSender.send(mailMessage);
    }
}
