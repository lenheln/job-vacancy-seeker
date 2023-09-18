package com.e_koslowa.jobvacancyseeker.parsers.jobcardparser;

import com.e_koslowa.jobvacancyseeker.JobVacancy;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class LinkedinJobCardParser extends AbstractJobCardParser {

    //to config
    private static final String scriptType = "script[type=application/ld+json]";
    private static final String jobNameProperty = "title";
    private static final String employeerHeader = "hiringOrganization";
    private static final String employeer = "name";
    private static final String description = "description";

    @Override
    public JobVacancy parseJobCard(Document document) {
        Element script = document.select(scriptType).first();
        if(script != null) {
            String json = script.html();
            JSONObject jsonObject = new JSONObject(json);
            return new JobVacancy(
                    jsonObject.getString(jobNameProperty),
                    jsonObject.getJSONObject(employeerHeader).getString(employeer),
                    jsonObject.getString(description)
            );
        } else
            return null;
    }
}
