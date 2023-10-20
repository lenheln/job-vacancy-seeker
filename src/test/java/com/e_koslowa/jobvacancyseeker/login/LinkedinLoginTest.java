package com.e_koslowa.jobvacancyseeker.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkedinLoginTest {

    @Autowired
    LinkedinLogin linkedinLogin;

    @Test
    public void linkedinLoginReturnsCorrectUrl()  {
        String expected = "https://www.linkedin.com/feed/";
        Assertions.assertEquals(expected, linkedinLogin.login().getCurrentUrl());
    }
}
