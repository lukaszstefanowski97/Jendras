package com.example.annihilator.utils;

import com.example.annihilator.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final EmailService emailService;

    public ScheduledTask(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000)
    void checkIp() {
        String currentIp = "";
        try {
            currentIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if (!currentIp.isEmpty() && EnvConfig.ip != null) {
            if (!currentIp.equals(EnvConfig.ip)) {
                log.info(Messages.IP_CHANGED + currentIp);
                emailService.notifyAboutIpChange(currentIp);
            }
        }
    }
}
