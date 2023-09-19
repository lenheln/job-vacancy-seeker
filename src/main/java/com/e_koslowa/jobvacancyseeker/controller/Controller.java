package com.e_koslowa.jobvacancyseeker.controller;

import com.e_koslowa.jobvacancyseeker.ScheduledImport;
import com.e_koslowa.jobvacancyseeker.YamlConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    ScheduledImport scheduledImport;

    @Autowired
    YamlConfig config;

    @GetMapping("/start")
    public void start() throws MessagingException, IOException {
        scheduledImport.doImport();
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() throws MessagingException, IOException {
        return new ResponseEntity<>(config.getEmailPassword(), HttpStatusCode.valueOf(200));
    }

}
