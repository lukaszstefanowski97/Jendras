package com.example.annihilator.controllers;

import com.example.annihilator.services.EmailService;
import com.example.annihilator.utils.EmailData;
import com.example.annihilator.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> sendEmail(@RequestBody EmailData emailData) {
        if (emailData.getContent() == null || emailData.getSubject() == null) {
            return new ResponseEntity<>(Messages.INBALID_REQUEST, HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(emailService.sendEmail(emailData.getSubject(), emailData.getContent()), HttpStatus.OK);
    }
}
