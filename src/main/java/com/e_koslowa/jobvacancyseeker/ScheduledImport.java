package com.e_koslowa.jobvacancyseeker;

import com.e_koslowa.jobvacancyseeker.emailsender.EmailSender;
import com.e_koslowa.jobvacancyseeker.filter.JavaDeveloperFilter;
import com.e_koslowa.jobvacancyseeker.htmlretriever.AbstractHtmlRetriever;
import com.e_koslowa.jobvacancyseeker.parsers.jobcardparser.AbstractJobCardParser;
import com.e_koslowa.jobvacancyseeker.parsers.jobslistparser.AbstractResultListParser;
import com.e_koslowa.jobvacancyseeker.parsers.jobslistparser.LinkedinFirstPageResultListParser;
import jakarta.mail.MessagingException;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ScheduledImport {

    private static final int jobsPerPage = 25; //todo config

    private static Logger log = Logger.getLogger(ScheduledImport.class.getName());

    @Autowired
    private YamlConfig config;

    @Autowired
    private AbstractHtmlRetriever htmlRetriever;

    @Autowired
    @Qualifier("linkedinResultListParser")
    private AbstractResultListParser linkedinResultParser;

    @Autowired
    @Qualifier("linkedinJobCardParser")
    private AbstractJobCardParser linkedinJobCardParser;

    @Autowired
    private LinkedinFirstPageResultListParser linkedinFirstPageParser;

    @Autowired
    private JavaDeveloperFilter javaDeveloperFilter;

    @Autowired
    private EmailSender emailSender;

    private Map<String,String> cookies = null;
    private List<String> errors = new LinkedList<>();

    @Scheduled(cron = "10 55 15 * * * ")
    public void doImport() throws IOException,  MessagingException {

        int pagesAmount = getTotalJobsAmount();
        List<String> urls = getJobsUrls(pagesAmount);
        log.info("Urls in total " + urls.size());

        List<JobVacancy> jobVacancies = getJobVacancies(urls);
        log.info("Successful processed jobs " + jobVacancies.size());

        List<JobVacancy> filteredJobs =
                jobVacancies.stream().filter(javaDeveloperFilter).toList();
        log.info("Jobs in total after filter " + filteredJobs.size());

        emailSender.sendEmail(filteredJobs, errors);
        errors.clear();
    }

    //todo write tests
    public int getTotalJobsAmount() throws IOException {

        Connection.Response firstPageResponse =  htmlRetriever.retrieveHtml(
                cookies, config.getLinkedinStartPage()+config.getLinkedinFilters());

        Document firstPage = firstPageResponse.parse();
        cookies = firstPageResponse.cookies();

        int totalResults = linkedinFirstPageParser.getTotalResults(firstPage);
        log.info("total jobs " + totalResults);

        int pagesAmount = (totalResults % 25 == 0) ? totalResults/25 : totalResults/25 + 1;
        return pagesAmount;
    }

    private List<JobVacancy> getJobVacancies(List<String> urls) throws IOException {

        List<JobVacancy> jobVacancies = new LinkedList<>();

        for (String url: urls) {

            Connection.Response jobPageResponse = htmlRetriever.retrieveHtml(cookies, url);

            if(jobPageResponse != null) {
                cookies = jobPageResponse.cookies();
                Document jobPage = jobPageResponse.parse();
                JobVacancy jobVacancy = linkedinJobCardParser.parseJobCard(jobPage);

                if(jobVacancy != null) {
                    jobVacancy.setLink(url);
                    jobVacancies.add(jobVacancy);
                } else {
                    errors.add(url);
                    log.warning("Job page can't be parsed " + url);
                }

            } else {
                errors.add(url);
                log.warning("Response is null " + url);
            }
        }
        return jobVacancies;
    }

    private List<String> getJobsUrls(int pagesAmount) throws IOException {

        List<String> urls = new LinkedList<>();

        for (int i = 0; i < pagesAmount; i++) {

            Connection.Response jobsListResponse =
                    htmlRetriever.retrieveHtml(
                            cookies, config.getLinkedinListPage() + config.getLinkedinFilters() + "&start=" + i * jobsPerPage);

            if(jobsListResponse != null) {
                Document jobsList = jobsListResponse.parse();
                cookies = jobsListResponse.cookies();
                urls.addAll(linkedinResultParser.parseResultList(jobsList));
            }
            else
                log.warning("Can't parse this link: " + config.getLinkedinListPage() + config.getLinkedinFilters() + "&start=" + i * jobsPerPage);
        }
        return urls;
    }
}
