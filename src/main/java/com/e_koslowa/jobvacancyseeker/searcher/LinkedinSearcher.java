package com.e_koslowa.jobvacancyseeker.searcher;

import com.e_koslowa.jobvacancyseeker.config.LinkedinConfig;
import com.e_koslowa.jobvacancyseeker.emailsender.EmailSender;
import com.e_koslowa.jobvacancyseeker.entity.Job;
import com.e_koslowa.jobvacancyseeker.entity.LinkedinRequestParams;
import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
import com.e_koslowa.jobvacancyseeker.filter.AbstractFilter;
import com.e_koslowa.jobvacancyseeker.login.LinkedinLogin;
import com.e_koslowa.jobvacancyseeker.parsers.LinkedinParser;
import com.e_koslowa.jobvacancyseeker.utils.LinkedinParamsMapper;
import jakarta.mail.MessagingException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class LinkedinSearcher {

    private static Logger log = Logger.getLogger(LinkedinSearcher.class.getName());

    @Autowired
    private LinkedinConfig config;
    @Autowired
    private LinkedinParser parser;
    @Autowired
    private LinkedinLogin login;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private LinkedinParamsMapper mapper;

    @Autowired
    @Qualifier("linkedinFilter")
    private AbstractFilter filter;

    //todo если унаследоваться от абстрактного класса, то это нарушит Лискова принцип
    public void search(LinkedinRequestParams linkedinRequestParams) throws MessagingException {

        WebDriver driver = login.login();

        List<LinkedinSearchParams> linkedinSearchParams = mapper.map(linkedinRequestParams);

        List<Job> jobs = new ArrayList<>();
        for (LinkedinSearchParams params: linkedinSearchParams) {
            jobs.addAll(parser.parse(params, driver));
        }
        log.info("JOBS IN TOTAL " + jobs.size());

        List<Job> filteredJobs = filter.filter(jobs);
        List<Job> filteredUniqueJobs = filteredJobs.stream().distinct().toList();
        if(!filteredUniqueJobs.isEmpty())
            emailSender.sendEmail(filteredJobs);

    }

    @Scheduled(cron = "10 55 15 * * * ")
    public void doScheduledImport() throws MessagingException, IOException {

        search(new LinkedinRequestParams(config.getDefaultLocation(),
                config.getDefaultKeywords(),
                config.getDefaultPostedSecAgo(),
                config.isDefaultRemote(),
                config.getDefaultLang()));
    }
}
