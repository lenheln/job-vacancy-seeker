package com.e_koslowa.jobvacancyseeker.utils;

import com.e_koslowa.jobvacancyseeker.entity.BasicRequestParams;

public abstract class AbstractUrlConstructor {

    private BasicRequestParams params;
    public abstract String getUrl();
}
