package com.e_koslowa.jobvacancyseeker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class LinkedinRequestParams extends BasicRequestParams {

    private List<String> locations;
    private Integer postedSecAgo;
    private boolean isRemote;
    private static final int SECONDS_IN_DAY = 86400;

    public LinkedinRequestParams(String locations, String keys, Integer postedDaysAgo, boolean isRemote, String lang) {
        super(keys, lang);
        this.locations = Arrays.asList(locations.split(","));
        this.postedSecAgo = (postedDaysAgo == null) ? null : postedDaysAgo * SECONDS_IN_DAY;
        this.isRemote = isRemote;
    }
}
