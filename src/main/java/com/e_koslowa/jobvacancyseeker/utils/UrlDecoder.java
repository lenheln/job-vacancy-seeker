package com.e_koslowa.jobvacancyseeker.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
public class UrlDecoder {

    public String decodeUrl(String url) {

        url = url.split("\\?")[0];
        url = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
        return url;
    }
}
