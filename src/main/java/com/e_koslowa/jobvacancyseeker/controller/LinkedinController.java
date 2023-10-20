package com.e_koslowa.jobvacancyseeker.controller;

import com.e_koslowa.jobvacancyseeker.entity.LinkedinRequestParams;
import com.e_koslowa.jobvacancyseeker.searcher.LinkedinSearcher;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class LinkedinController {

    private static final Logger log = Logger.getLogger(LinkedinController.class.getName());

    @Autowired
    LinkedinSearcher searcher;

//    @Autowired
//    StepstoneSearcher stepstoneSearcher;

    @GetMapping("/linkedin/")
    public ResponseEntity linkedin(
                      @RequestParam String locations,
                      @RequestParam String keys,
                      @RequestParam(required = false) Integer postedDaysAgo,
                      @RequestParam(required = false) boolean isRemote,
                      @RequestParam(required = false) String lang) {

        ExecutorService es = Executors.newFixedThreadPool(10);
        es.execute(() ->
        {
            try {
                searcher.search(new LinkedinRequestParams(locations, keys, postedDaysAgo, isRemote, lang));
            } catch (MessagingException e) {
                log.severe(e.getMessage());
            }
        });
        return new ResponseEntity<>("Your request is being processed", HttpStatus.OK);
    }

//    @GetMapping("/stepstone/")
//    public ResponseEntity steptone() {
//        //todo the same
//        stepstoneSearcher.setParams(new StepStoneParams());
//        stepstoneSearcher.search();
//        return new ResponseEntity<>("Your request is being processed", HttpStatus.OK);
//    }
}
