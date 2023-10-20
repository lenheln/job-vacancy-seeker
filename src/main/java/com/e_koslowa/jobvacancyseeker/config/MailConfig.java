package com.e_koslowa.jobvacancyseeker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
@ConfigurationProperties

@Getter
@Setter
public class MailConfig {

    @Value("${mail.smtp.auth}")
    private boolean smtpAuth;
    @Value("${mail.smtp.host}")
    private String smtpHost;
    @Value("${mail.smtp.port}")
    private String smtpPort;
    @Value("${mail.smtp.starttls.enable}")
    private boolean smtpTls;
    @Value("${mail.from}")
    private String emailFrom;
    @Value("${mail.to}")
    private String emailTo;
    @Value("${mail.title}")
    private String emailTitle;

    //    @Value("${sm://EMAIL_USERNAME}")
    @Value("${variables.email.username}")
    private String emailUsername;
    //    @Value("${sm://EMAIL_PASSWORD}")
    @Value("${variables.email.password}")
    private String emailPassword;
}
