package com.e_koslowa.jobvacancyseeker.entity;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class LinkedinSearchParams extends BasicRequestParams {

    private String location;
    private Integer postedSecAgo;
    private boolean isRemote;

    public LinkedinSearchParams(String location, String keys, Integer postedSecAgo, boolean isRemote, String lang) {
        super(keys, lang);
        this.location = location;
        this.postedSecAgo = postedSecAgo;
        this.isRemote = isRemote;
    }
}
