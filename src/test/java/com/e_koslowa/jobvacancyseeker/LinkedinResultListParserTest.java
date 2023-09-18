//package com.e_koslowa.jobvacancyseeker;
//
//import com.e_koslowa.jobvacancyseeker.parsers.jobslistparser.LinkedinResultListParser;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//@SpringBootTest(args={"--EMAIL_USERNAME=test@gmail.com","--EMAIL_PASSWORD=******"})
//public class LinkedinResultListParserTest {
//
//    @Autowired
//    @Qualifier("linkedinResultListParser")
//    private LinkedinResultListParser parser;
//
//    @Test
//    void parseResultListContainsExpectedUrl() throws IOException {
//
//        String html = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/linkedin_result_list.html")));
//        Document document = Jsoup.parse(html);
//        List<String> urls = parser.parseResultList(document);
//        String expectedURL = "https://de.linkedin.com/jobs/view/senior-java-software-engineer-f-m-x-listed-derivatives-at-jobs-via-efinancialcareers-3690762987";
//        Assertions.assertTrue(urls.contains(expectedURL));
//    }
//
//    @Test
//    void parseResultListContainsExpectedAmountOfUrls() throws IOException {
//
//        String html = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/linkedin_jobs_list.html")));
//        Document document = Jsoup.parse(html);
//        List<String> urls = parser.parseResultList(document);
//        Assertions.assertEquals(25, urls.size());
//    }
//}
