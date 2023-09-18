package com.e_koslowa.jobvacancyseeker.filter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class StopWordsGetter {

    private static Logger log = Logger.getLogger(StopWordsGetter.class.getName());

    public List<String> getStopWords(String filename) {

        List<String> stopWords = new LinkedList<>();
        try {
            InputStream inputStream = StopWordsGetter.class.getResourceAsStream("/" + filename);
            if(inputStream != null) {
                String data = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                stopWords = Arrays.stream(data.split("\r\n")).toList();
            }
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
        return stopWords;
    }
}
