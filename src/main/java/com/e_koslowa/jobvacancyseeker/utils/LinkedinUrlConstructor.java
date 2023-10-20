package com.e_koslowa.jobvacancyseeker.utils;

import com.e_koslowa.jobvacancyseeker.config.LinkedinConfig;
import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkedinUrlConstructor {

    @Autowired
    private LinkedinConfig config;

    private static final String LINKEDIN_PARAM_LOCATION = "location=";
    private static final String LINKEDIN_PARAM_POSTED_AGO = "f_TPR=r";
    private static final String LINKEDIN_PARAM_KEYWORDS = "keywords=";
    private static final String LINKEDIN_PARAM_REMOTE = "f_WT=2";

    public String getLinkedinUrl(LinkedinSearchParams param) {

        if(param.getPostedSecAgo() == null)
            param.setPostedSecAgo(config.getDefaultPostedSecAgo());

        if(!param.isRemote())
            param.setRemote(config.isDefaultRemote());

        if(param.getLang() == null)
                param.setLang(config.getDefaultLang());

        String keywords = String.join("+", param.getKeys().split(","));
        String url = "?" + LINKEDIN_PARAM_LOCATION + param.getLocation() +
                "&" + LINKEDIN_PARAM_POSTED_AGO + param.getPostedSecAgo() +
                "&" + LINKEDIN_PARAM_KEYWORDS + keywords;
        return (param.isRemote()) ? url + "&" + LINKEDIN_PARAM_REMOTE : url;
    }
}
