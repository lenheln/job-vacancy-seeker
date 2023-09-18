package com.e_koslowa.jobvacancyseeker;

import java.util.Objects;

public class JobVacancy {

    private String jobName;
    private String employeer;
    private String description;
    private String shortDescription;
    private String link;

    public JobVacancy(String jobName, String employeer, String description) {
        this.jobName = jobName;
        this.employeer = employeer;
        this.description = description;
    }

    public JobVacancy(String jobName, String employeer, String description, String link) {
        this.jobName = jobName;
        this.employeer = employeer;
        this.description = description;
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getJobName() {
        return jobName;
    }

    public String getLink() {
        return link;
    }

    public String getEmployeer() {
        return employeer;
    }

    public String getShortDescription() {
        shortDescription = (description.length() > 300) ? description.substring(0,300) : description;
        return shortDescription.replaceAll("\\&lt;[^(\\&gt)]*\\&gt;", "").replaceAll("&lt;strong&gt;","");
    }

    @Override
    public boolean equals(Object o) {
        //todo бывают одинаковые названия но в разных городах
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobVacancy that = (JobVacancy) o;
        return Objects.equals(jobName, that.jobName) && Objects.equals(employeer, that.employeer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, employeer);
    }

    @Override
    public String toString() {

        return  jobName + "\n" +
                employeer + "\n" +
                getShortDescription() + "\n" +
                link + "\n";
    }
}
