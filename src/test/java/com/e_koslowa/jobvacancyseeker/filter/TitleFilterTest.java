package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.config.FilesConfig;
import com.e_koslowa.jobvacancyseeker.entity.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TitleFilterTest {

    @Autowired
    BasicFilter basicFilter;
    @Mock
    FilesConfig config;
    @InjectMocks
    TitleFilter titleFilter;

    @Test
    public void text_filter_return_true() {

        Job job = new Job("Java Developer", "Google", "10000+", "Munchen", "Super job");
        Mockito.when(config.getStopTitles()).thenReturn("test-stop-words.txt");
        Assertions.assertTrue(titleFilter.test(job));
    }

}