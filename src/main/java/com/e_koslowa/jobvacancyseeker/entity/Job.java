package com.e_koslowa.jobvacancyseeker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Setter @Getter
public class Job {

    private String title;
    private String company;
    private String employees;
    private String location;
    private String description;
    private String link;
    private static final int SHORT_DESCRIPTION_SIZE = 500;

    public Job(String title, String company, String employees, String location, String description) {
        this.title = title;
        this.company = company;
        this.employees = employees;
        this.location = location;
        this.description = description;
    }


    public Job(String title, String company, String employees, String location, String description, String link) {
        this.title = title;
        this.company = company;
        this.employees = employees;
        this.location = location;
        this.description = description;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        //todo бывают одинаковые названия но в разных городах
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job that = (Job) o;
        return Objects.equals(title, that.title) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, company);
    }

    @Override
    public String toString() {

        String shortDescription = (description.length() > SHORT_DESCRIPTION_SIZE) ?
                description.substring(0,SHORT_DESCRIPTION_SIZE) : description;

        return  title + "\n" +
                company + "\n" +
                employees + "\n" +
                location + "\n" +
                shortDescription + "\n" +
                link + "\n";
    }
}
