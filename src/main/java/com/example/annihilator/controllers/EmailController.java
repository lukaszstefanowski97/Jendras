package com.example.annihilator.controllers;

import com.example.annihilator.services.EmailService;
import com.example.annihilator.utils.EmailData;
import com.example.annihilator.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("api")
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/test")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>(Messages.TEST_MESSAGE, HttpStatus.OK);
    }

    @PostMapping("/beginDestruction")
    public ResponseEntity<String> sendEmail(@RequestBody EmailData emailData, BindingResult bindingResult)
            throws IOException, MessagingException, InterruptedException {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Messages.VALIDATION, HttpStatus.BAD_REQUEST);
        }
        emailService.beginDestruction(emailData.getSubject(), emailData.getContent());
        return new ResponseEntity<>(Messages.EMAIL_SUCCEDED, HttpStatus.OK);
    }
}
