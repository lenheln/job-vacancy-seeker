package com.e_koslowa.jobvacancyseeker.parsers;

import com.e_koslowa.jobvacancyseeker.config.LinkedinConfig;
import com.e_koslowa.jobvacancyseeker.entity.BasicRequestParams;
import com.e_koslowa.jobvacancyseeker.entity.Job;
import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
import com.e_koslowa.jobvacancyseeker.utils.LinkedinUrlConstructor;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class LinkedinParser {

    @Autowired
    private LinkedinUrlConstructor urlConstructor;
    @Autowired
    private LinkedinConfig config;

    private static Logger log = Logger.getLogger(LinkedinParser.class.getName());
    private static final int JOBS_PER_PAGE = 25;
    private static final String JOB_LEFT_CARD_CSS= "div[class^='job-card-container relative']";

    public List<Job> parse(LinkedinSearchParams linkedinUrlParam, WebDriver driver) {

        int pagesAmount = getPagesAmount(linkedinUrlParam, driver);
        List<Job> allJobs = new LinkedList<>();

        for(int i = 0; i < pagesAmount; i++) {

            String page = config.getJobSearchPage()
                    + urlConstructor.getLinkedinUrl(linkedinUrlParam)
                    + "&start=" + i * JOBS_PER_PAGE;

            driver.get(page);
            allJobs.add(parseJob(driver)); //parse active job

            for(int j = 1; j < JOBS_PER_PAGE; j++) {
                List<WebElement> jobs = driver.findElements(By.cssSelector(JOB_LEFT_CARD_CSS));

                if(jobs.size() > j) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobs.get(j));

                    int attempts = 0;
                    while(attempts < 3) {
                        try {
                            driver.findElements(By.cssSelector(JOB_LEFT_CARD_CSS)).get(j).click();
                            break;
                        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException ignored) {
                        }
                        attempts++;
                    }
                    allJobs.add(parseJob(driver));
                } else
                    break;
            }
        }
        return allJobs;
    }

    private static final String JOBS_AMOUNT_XPATH = "//*[@id=\"main\"]/div/div[1]/header/div[1]/small/div/span";

    private int getPagesAmount(LinkedinSearchParams linkedinUrlParam, WebDriver driver) {

        String jobsUrl = config.getJobSearchPage() + urlConstructor.getLinkedinUrl(linkedinUrlParam);
        driver.get(jobsUrl);

        String jobsAmountString = "0";
        int attempts = 0;
        while(attempts < 3) {
            try {
                jobsAmountString = driver.findElement(By.xpath(JOBS_AMOUNT_XPATH)).getText();
                break;
            } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException ignored) {
            }
            attempts++;
        }

        jobsAmountString = jobsAmountString.replaceAll(",","");
        int jobsAmount = Integer.parseInt(jobsAmountString.substring(0, jobsAmountString.indexOf(" ")));
        return (jobsAmount % 25 == 0) ? jobsAmount/25 : jobsAmount/25 + 1;
    }

    private static final String JOB_TITLE_XPATH = "//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div[1]";
    private static final String JOB_EMPLOYEES_XPATH = "//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div[3]/ul/li[2]/span";
    private static final String JOB_EMPLOYEER_XPATH = "//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div[2]/div/a";
    private static final String JOB_LOCATION_XPATH = "//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div[2]/div";
    private static final String JOB_DESCRIPTION_XPATH = "//*[@id=\"job-details\"]";

    private Job parseJob(WebDriver driver) {

        String title, employees, company, location, desciption, link;
        title = employees = company = location = desciption = link = "";

        try {
            WebElement headerDiv = driver.findElement(By.xpath(JOB_TITLE_XPATH));
            WebElement headerA = headerDiv.findElement(By.tagName("a"));
            title =  headerA.findElement(By.tagName("h2")).getText();
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException e) {
            log.info("Error occured\n" + e);
        }
        try {
            employees = driver.findElement(By.xpath(JOB_EMPLOYEES_XPATH)).getText();
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException  e) {
            log.info("Error occured\n" + e);
        }
        try {
            company = driver.findElement(By.xpath(JOB_EMPLOYEER_XPATH)).getText();
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException  e) {
            log.info("Error occured\n" + e);
        }
        try {
            location = driver.findElement(By.xpath(JOB_LOCATION_XPATH)).getText();
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException  e) {
            log.info("Error occured\n" + e);
        }
        try {
            desciption = driver.findElement(By.xpath(JOB_DESCRIPTION_XPATH)).getText();
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException  e) {
            log.info("Error occured\n" + e);
        }
        try {
             WebElement headerDiv = driver.findElement(By.xpath(JOB_TITLE_XPATH));
             link = headerDiv.findElement(By.tagName("a")).getAttribute("href");
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException  e) {
            log.info("Error occured\n" + e);
        }

        return new Job(title, company, employees, location, desciption, link);
    }

}
