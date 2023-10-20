package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LinkedinFilter extends AbstractFilter{

    @Autowired
    private TitleFilter titleFilter;
    @Autowired
    private DescriptionFilter descriptionFilter;
    @Autowired
    private EmployeeFilter employeeFilter;
    @Autowired
    private LocationFilter locationFilter;

    @Override
    public List<Job> filter(List<Job> jobs) {

        return jobs.stream()
                .filter(titleFilter)
                .filter(locationFilter)
                .filter(employeeFilter)
                .filter(descriptionFilter)
                .collect(Collectors.toList());
    }
}
