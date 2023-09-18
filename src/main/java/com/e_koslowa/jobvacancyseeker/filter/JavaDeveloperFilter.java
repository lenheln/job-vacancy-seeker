package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.JobVacancy;
import com.e_koslowa.jobvacancyseeker.YamlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

//todo добавить по численности
//todo если он сайт, то запрет на определенные города типа берлин, штутгарт, мюнхен

@Component
public class JavaDeveloperFilter implements Predicate<JobVacancy> {

    @Autowired
    private StopWordsGetter stopWordsGetter;

    @Autowired
    private YamlConfig config;

    @Override
    public boolean test(JobVacancy job) {

        String name = job.getJobName().toLowerCase();
        for (String forbiddenName: stopWordsGetter.getStopWords(config.getStopNamesFile())) {
            if(name.contains(forbiddenName))
                return false;
        }

        String description = job.getDescription().toLowerCase();
        for (String word: stopWordsGetter.getStopWords(config.getStopWordsFile())) {
            if(description.contains(word))
                return false;
        }

        return true;
    }
}
