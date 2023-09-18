package com.e_koslowa.jobvacancyseeker.controller;

import com.e_koslowa.jobvacancyseeker.ScheduledImport;
import jakarta.mail.MessagingException;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/start")
public class Controller {

    @Autowired
    ScheduledImport scheduledImport;

    @GetMapping
    public void start() throws MessagingException, IOException {
        scheduledImport.doImport();
    }
    
}
