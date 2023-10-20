//package com.e_koslowa.jobvacancyseeker.searcher;
//
//import com.e_koslowa.jobvacancyseeker.emailsender.EmailSender;
//import com.e_koslowa.jobvacancyseeker.entity.Job;
//import com.e_koslowa.jobvacancyseeker.entity.LinkedinRequestParams;
//import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
//import com.e_koslowa.jobvacancyseeker.login.AbstractLogin;
//import com.e_koslowa.jobvacancyseeker.parsers.AbstractParser;
//import lombok.Setter;
//import org.openqa.selenium.WebDriver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Setter
//public abstract class AbstractSearcher {
//
//    private EmailSender emailSender;
//    private CommonRequetsParams params; //todo set them in setter instead of passing in method
//
//    public void setParams(CommonRequetsParams params) {
//        this.params = params;
//    }
//
//    public void search(LinkedinRequestParams linkedinRequestParams) {
//
//        AbstractLogin login = createLogin();
//        AbstractMapper mapper = createMapper();
//        AbstractParser parser = createParser();
//        AbstractFilter filter = createFilter();
//
//        WebDriver driver = login.login();
//
//        List<LinkedinSearchParams> linkedinSearchParams = mapper.map(linkedinRequestParams);
//
//        List<Job> jobs = new ArrayList<>();
//        for (LinkedinSearchParams params: linkedinSearchParams) {
//            jobs.addAll(parser.parse(params, driver));
//        }
//        log.info("JOBS IN TOTAL " + jobs.size());
//
//        List<Job> filteredJobs = filter.filter(jobs);
//        List<Job> filteredUniqueJobs = filteredJobs.stream().distinct().toList();
//        if(!filteredUniqueJobs.isEmpty())
//            emailSender.sendEmail(filteredJobs);
//    }
//
//    abstract AbstractLogin createLogin();
//    abstract AbstractMapper createMapper();
//    abstract AbstractParser createParser();
//    abstract AbstractFilter createFilter();
//
//
//}
