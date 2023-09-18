package com.e_koslowa.jobvacancyseeker.htmlretriever;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class HtmlRetriever extends AbstractHtmlRetriever {

    Logger log = Logger.getLogger(HtmlRetriever.class.getName());
    int maxAttempts = 3;

    @Override
    public Connection.Response retrieveHtml(Map<String,String> cookies, String url) {

        Connection.Response response = null;
        try {
            Connection connection = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36") //todo config
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true);

            if(cookies != null)
                connection.cookies(cookies);

            response = connection.execute();
            maxAttempts = 3;

        } catch (HttpStatusException e) {

            if((e.getStatusCode() == 429) && (maxAttempts > 0)) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                maxAttempts--;
                response = this.retrieveHtml(cookies, url);

            } else {
                log.warning("Page can't be retrieved. Status code " + e.getStatusCode() + " URL " + url );
            }

        } catch (IOException e) {
            log.severe("Connection error. " + e.getMessage());
        }
        return response;
    }

}
