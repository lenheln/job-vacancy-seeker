package com.e_koslowa.jobvacancyseeker;

import com.e_koslowa.jobvacancyseeker.emailsender.EmailSender;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    EmailSender sender;

    @Test
    void createEmail() throws MessagingException {

        JobVacancy job1 = new JobVacancy("Senior Java Developer",
                "Google",
                "'<p>Join Our Client’s Team: <u>Database and Software Engineer for NATO Project</u></p><p><br></p><p>We are looking for an exceptional Database and Software Engineer to our client’s team for a high-impact project in collaboration with NATO.</p>",
                "http://google.com");
        JobVacancy job2 = new JobVacancy("Junior Java Developer",
                "Docker",
                "<p>Als Full-Stack Developer bist Du die treibende Kraft bei der Weiterentwicklung unserer Plattform.</p><p><br></p><p><strong>Deine Aufgaben umfassen im Detail:</strong></p>",
                "http://docker.com");

        List<JobVacancy> jobs = new LinkedList<>(Arrays.asList(job1,job2));
        List<String> errors = new LinkedList<>(Arrays.asList("http://error1.com","http://error2.com"));

        String expectedEmail = "<html>\n" +
                                "Senior Java Developer\n" +
                                "Google\n" +
                                "'<p>Join Our Client’s Team: <u>Database and Software Engineer for NATO Project</u></p><p><br></p><p>\n" +
                                "http://google.com\n" +
                                "\n" +
                                " ---- \n" +
                                "Junior Java Developer\n" +
                                "Docker\n" +
                                "<p>Als Full-Stack Developer bist Du die treibende Kraft bei der Weiterentwicklung unserer Plattform.\n" +
                                "http://docker.com\n" +
                                "Errors \n" +
                                "http://error1.com\n" +
                                "http://error2.com\n" +
                                "</html>";

        Assertions.assertEquals(expectedEmail, sender.createEmail(jobs, errors));

    }
}
