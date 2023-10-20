package com.e_koslowa.jobvacancyseeker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
@ConfigurationProperties

@Getter
@Setter
public class LinkedinConfig {

    @Value("${linkedin.startPage}")
    private String startPage;
    @Value("${linkedin.listPage}")
    private String listPage;
    @Value("${linkedin.loginPage}")
    private String loginPage;
    @Value("${linkedin.jobSearchPage}")
    private String jobSearchPage;
    @Value("${linkedin.default.location}")
    private String defaultLocation;
    @Value("${linkedin.default.keywords}")
    private String defaultKeywords;
    @Value("${linkedin.default.postedSecAgo}")
    private Integer defaultPostedSecAgo;
    @Value("${linkedin.default.remote}")
    private boolean defaultRemote;
    @Value("${linkedin.default.lang}")
    private String defaultLang;
    @Value("${linkedin.credentials.username}")
    private String user;
    @Value("${linkedin.credentials.password}")
    private String password;
}
