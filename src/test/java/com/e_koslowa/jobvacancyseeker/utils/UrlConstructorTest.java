package com.e_koslowa.jobvacancyseeker.utils;

import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UrlConstructorTest {

    @Autowired
    LinkedinUrlConstructor urlConstructor;

    @Test
    public void constructLinkedinUrlWithRemoteTrueReturnsCorrectUrl() {

        String location = "Germany";
        String keywords = "java,developer";
        int postedDaysAgo = 1;
        boolean remote = true;
        String lang = "EN";
        LinkedinSearchParams linkedinUrlParam = new LinkedinSearchParams(location, keywords, postedDaysAgo, remote, lang);

        String expected = "?location=Germany&f_TPR=r86400&keywords=java+developer&f_WT=2";
        Assertions.assertEquals(expected, urlConstructor.getLinkedinUrl(linkedinUrlParam));
    }

    @Test
    public void constructLinkedinUrlWithRemoteFalseReturnsCorrectUrl() {

        String location = "Germany";
        String keywords = "java,developer";
        int postedDaysAgo = 1;
        boolean remote = false;
        String lang = "EN";
        LinkedinSearchParams linkedinUrlParam = new LinkedinSearchParams(location, keywords, postedDaysAgo, remote, lang);

        String expected = "?location=Germany&f_TPR=r86400&keywords=java+developer";
        Assertions.assertEquals(expected, urlConstructor.getLinkedinUrl(linkedinUrlParam));
    }
}
