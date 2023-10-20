package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.utils.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicFilter {

    @Autowired
    private FileReader fileReader;

    public boolean test(String str, String file) {

        String text = str.toLowerCase();
        for (String stopWord: fileReader.readFile(file)) {
            if(text.contains(stopWord))
                return false;
        }
        return true;
    }
}
