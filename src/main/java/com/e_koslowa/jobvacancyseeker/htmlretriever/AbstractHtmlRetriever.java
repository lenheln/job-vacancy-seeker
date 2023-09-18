package com.e_koslowa.jobvacancyseeker.htmlretriever;

import org.jsoup.Connection;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractHtmlRetriever {

    public abstract Connection.Response retrieveHtml(Map<String,String> cookies, String url);

}
