package com.e_koslowa.jobvacancyseeker.parsers.jobslistparser;

import org.jsoup.nodes.Document;

import java.util.List;


public abstract class AbstractResultListParser {

    public abstract List<String> parseResultList(Document doc);
}
