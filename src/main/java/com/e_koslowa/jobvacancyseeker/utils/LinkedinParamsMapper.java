package com.e_koslowa.jobvacancyseeker.utils;

import com.e_koslowa.jobvacancyseeker.entity.LinkedinRequestParams;
import com.e_koslowa.jobvacancyseeker.entity.LinkedinSearchParams;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class LinkedinParamsMapper {

    public List<LinkedinSearchParams> map(LinkedinRequestParams reqParams) {

        List<LinkedinSearchParams> searchParam = new LinkedList<>();
        for (String location: reqParams.getLocations()) {
            searchParam.add(new LinkedinSearchParams(
                    location,
                    reqParams.getKeys(),
                    reqParams.getPostedSecAgo(),
                    reqParams.isRemote(),
                    reqParams.getLang())
            );
        }
        return searchParam;
    }
}
