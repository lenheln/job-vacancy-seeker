package com.e_koslowa.jobvacancyseeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobVacancySeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobVacancySeekerApplication.class, args);
	}

}
