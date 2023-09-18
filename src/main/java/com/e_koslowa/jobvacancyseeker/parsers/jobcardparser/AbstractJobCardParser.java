package com.e_koslowa.jobvacancyseeker.parsers.jobcardparser;

import com.e_koslowa.jobvacancyseeker.JobVacancy;
import org.jsoup.nodes.Document;

public abstract class AbstractJobCardParser {

    public abstract JobVacancy parseJobCard(Document document);
}
