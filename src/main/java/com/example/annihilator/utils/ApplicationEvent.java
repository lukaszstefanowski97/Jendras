package com.example.annihilator.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class ApplicationEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    {
        try {
            EnvConfig.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public ApplicationEvent(Environment environment) {
        this.environment = environment;
    }

    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info(Messages.APP_RUNNING);
        if (EnvConfig.ip == null) {
            log.error(Messages.IP_ERROR);
        } else {
            log.info(Messages.LISTENING + EnvConfig.ip + ":" + environment.getProperty("local.server.port") + "/api/test");
            log.info(Messages.SEND_EMAIL + EnvConfig.ip + ":" + environment.getProperty("local.server.port") + "/api" +
                    "/beginDestruction");
        }
    }
}