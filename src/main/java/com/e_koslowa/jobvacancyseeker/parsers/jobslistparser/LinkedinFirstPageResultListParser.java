package com.e_koslowa.jobvacancyseeker.parsers.jobslistparser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class LinkedinFirstPageResultListParser extends LinkedinResultListParser {

    public int getTotalResults(Document doc) {
        Element div = doc.getElementsByClass("results-context-header").first(); //todo conf
        return Integer.parseInt(div.getElementsByClass("results-context-header__job-count")
                .first().text());
    }
}
