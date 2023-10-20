package com.e_koslowa.jobvacancyseeker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
@ConfigurationProperties

@Getter
@Setter
public class FilesConfig {

    private String stopTitles;
    private String stopDescriptions;
    private String stopLocations;
    private String stopEmployees;
}
