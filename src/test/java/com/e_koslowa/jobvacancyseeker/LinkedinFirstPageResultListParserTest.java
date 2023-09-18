package com.e_koslowa.jobvacancyseeker;

import com.e_koslowa.jobvacancyseeker.parsers.jobslistparser.LinkedinFirstPageResultListParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootTest
public class LinkedinFirstPageResultListParserTest {

    @Autowired
    LinkedinFirstPageResultListParser parser;

    @Test
    void getTotalResultReturnsCorrectly() {

        String html = null;
        try {
            html = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/linkedin_result_list.html")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(html);
        int totalResult = parser.getTotalResults(document);
        Assertions.assertEquals(totalResult, 111);

    }
}
