package com.e_koslowa.jobvacancyseeker.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FileReader {

    private static Logger log = Logger.getLogger(FileReader.class.getName());

    public List<String> readFile(String filename) {

        List<String> stopWords = new LinkedList<>();
        try {
            InputStream inputStream = FileReader.class.getResourceAsStream("/" + filename);
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
