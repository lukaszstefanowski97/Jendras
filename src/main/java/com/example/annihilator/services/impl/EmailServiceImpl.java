package com.example.annihilator.services.impl;

import com.example.annihilator.services.EmailService;
import com.example.annihilator.utils.Messages;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@Data
@Slf4j
@ConfigurationProperties(prefix = "mail")
public class EmailServiceImpl implements EmailService {

    private String sender;
    private String pass;
    private String konrad1;
    private String konrad2;
    private String konrad3;

    @Override
    public String sendEmail(String subject, String content) throws MessagingException {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", 587);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.user", getSender());
//        props.put("mail.password", getPass());
//
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(getSender(), getPass());
//            }
//        });
//
//        try {
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(getSender()));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getKonrad1()));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getKonrad2()));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getKonrad3()));
//            msg.setSubject(subject);
//            msg.setText(content);
//            Transport transport = session.getTransport("smtp");
//            transport.connect("smtp.gmail.com", getSender(), getPass());
//            transport.sendMessage(msg, msg.getAllRecipients());
//            transport.close();
//            log.info(Messages.EMAIL_SUCCEDED);
//            return Messages.EMAIL_SUCCEDED;
//        } catch (AddressException e) {
//            log.error(e.getMessage());
//            return e.getMessage();
//        } catch (MessagingException e) {
//            return e.getMessage();
//        }
        InternetAddress[] distributionList = InternetAddress.parse("lukasz.stefanowski97@gmail.com,olakadz@gmail.com" +
                ".com",false);
        String from = "javahonk@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);

        Message msg = new MimeMessage(session);
        msg.setContent(content, "text/html; charset=utf-8");
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, distributionList);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        Transport.send(msg);
        return Messages.EMAIL_SUCCEDED;
    }

    @Override
    public String checkIp() {
        return null;
    }
}
