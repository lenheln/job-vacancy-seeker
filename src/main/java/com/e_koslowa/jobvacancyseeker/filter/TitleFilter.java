package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.config.FilesConfig;
import com.e_koslowa.jobvacancyseeker.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class TitleFilter implements Predicate<Job> {

    @Autowired
    BasicFilter filter;
    @Autowired
    FilesConfig config;

    @Override
    public boolean test(Job job) {

        return filter.test(job.getTitle(), config.getStopTitles());
    }
}
