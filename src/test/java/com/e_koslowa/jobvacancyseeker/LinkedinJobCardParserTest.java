package com.e_koslowa.jobvacancyseeker;

import com.e_koslowa.jobvacancyseeker.parsers.jobcardparser.LinkedinJobCardParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class LinkedinJobCardParserTest {

    @Autowired
    private LinkedinJobCardParser parser;

    @Test
    void parseJobCardCreatesCorrectCard() throws IOException {

        String html = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/linkedin_job_card.html")));
        Document document = Jsoup.parse(html);
        JobVacancy jobVacancy = parser.parseJobCard(document);
        JobVacancy expectedJobVacancy = new JobVacancy("Java Developer (m/f/d)", "JustRelate Group", "");
        Assertions.assertEquals(expectedJobVacancy, jobVacancy);
    }
}
