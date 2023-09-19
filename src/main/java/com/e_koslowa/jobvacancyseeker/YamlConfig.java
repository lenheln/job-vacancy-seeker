package com.e_koslowa.jobvacancyseeker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
@ConfigurationProperties

public class YamlConfig {

    private String stopNamesFile;
    private String stopWordsFile;

    private String linkedinStartPage;
    private String linkedinListPage;
    private String linkedinFilters;

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

    @Value("${sm://variables.email.username}")
    private String emailUsername;
    @Value("${sm://variables.email.password}")
    private String emailPassword;

    /* getters and setters */

    public String getStopNamesFile() {
        return stopNamesFile;
    }

    public void setStopNamesFile(String stopNamesFile) {
        this.stopNamesFile = stopNamesFile;
    }

    public String getStopWordsFile() {
        return stopWordsFile;
    }

    public void setStopWordsFile(String stopWordsFile) {
        this.stopWordsFile = stopWordsFile;
    }

    public String getLinkedinStartPage() {
        return linkedinStartPage;
    }

    public void setLinkedinStartPage(String linkedinStartPage) {
        this.linkedinStartPage = linkedinStartPage;
    }

    public String getLinkedinListPage() {
        return linkedinListPage;
    }

    public void setLinkedinListPage(String linkedinListPage) {
        this.linkedinListPage = linkedinListPage;
    }

    public String getLinkedinFilters() {
        return linkedinFilters;
    }

    public void setLinkedinFilters(String linkedinFilters) {
        this.linkedinFilters = linkedinFilters;
    }

    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isSmtpTls() {
        return smtpTls;
    }

    public void setSmtpTls(boolean smtpTls) {
        this.smtpTls = smtpTls;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailUsername() {
        return emailUsername;
    }

    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }
}
