package com.e_koslowa.jobvacancyseeker.parsers.jobslistparser;

import com.e_koslowa.jobvacancyseeker.utils.UrlDecoder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class LinkedinResultListParser extends AbstractResultListParser {

    //todo to config
    private static final String divClassName = "base-card relative w-full hover:no-underline focus:no-underline base-card--link base-search-card base-search-card--link job-search-card";

    @Autowired
    private UrlDecoder urlDecoder;

    private static Logger log = Logger.getLogger(LinkedinResultListParser.class.getName());

    @Override
    public List<String> parseResultList(Document doc) {

        List<String> urls = new LinkedList();

        Elements divs = doc.getElementsByClass(divClassName);

        for (Element div: divs) {
            Element a = div.select("a").first();
            if(a != null) {
                String url = a.attr("href");
                urls.add(urlDecoder.decodeUrl(url));
            } else {
                log.warning("attribute href wasn't found");
            }
        }
        return urls;
    }
}
