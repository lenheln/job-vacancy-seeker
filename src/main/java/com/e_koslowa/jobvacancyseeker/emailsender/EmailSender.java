package com.e_koslowa.jobvacancyseeker.emailsender;

import com.e_koslowa.jobvacancyseeker.JobVacancy;
import com.e_koslowa.jobvacancyseeker.YamlConfig;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class EmailSender {

    private static final Logger log = Logger.getLogger(EmailSender.class.getName());

    @Autowired
    private YamlConfig config;

    public void sendEmail(List<JobVacancy> jobs, List<String> errors) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", config.isSmtpAuth());
        prop.put("mail.smtp.host", config.getSmtpHost());
        prop.put("mail.smtp.port", config.getSmtpPort());
        prop.put("mail.smtp.starttls.enable", config.isSmtpTls());

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getEmailUsername(), config.getEmailPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(config.getEmailFrom()));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(config.getEmailTo()));
        message.setSubject(config.getEmailTitle());

        String body = createEmail(jobs, errors);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/plain; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        Transport.send(message);
    }

    public String createEmail(List<JobVacancy> jobs, List<String> errors) {

        String body = jobs.stream().map(Object::toString).collect(Collectors.joining("\n ---- \n"));
        body = body.concat("Errors \n");
        body += errors.stream().map(Objects::toString).collect(Collectors.joining("\n"));

        return body;
    }
}
