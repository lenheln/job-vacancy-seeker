package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.entity.Job;

import java.util.List;

public abstract class AbstractFilter {

    public abstract List<Job> filter(List<Job> jobs);
}
