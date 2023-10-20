package com.e_koslowa.jobvacancyseeker.parsers;

import com.e_koslowa.jobvacancyseeker.entity.BasicRequestParams;
import com.e_koslowa.jobvacancyseeker.entity.Job;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Setter
public abstract class AbstractParser {

    private BasicRequestParams params;
    public abstract List<Job> parse(WebDriver driver);
}
