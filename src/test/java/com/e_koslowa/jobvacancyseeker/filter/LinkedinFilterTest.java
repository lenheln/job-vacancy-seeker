//package com.e_koslowa.jobvacancyseeker.filter;
//
//import com.e_koslowa.jobvacancyseeker.config.FilesConfig;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.internal.matchers.Any;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//public class LinkedinFilterTest {
//
//    @Autowired
//    @Qualifier("linkedinFilter")
//    AbstractFilter filter;
//
//    @Test
//    public void filter_remove_employees_from_the_list() {
//
//        List<String> file = Arrays.asList("51-200", "senior");
//        FileReader reader = Mockito.mock(FileReader.class);
//        Mockito.when(reader.read(Mockito.anyString())).thenReturn(file);
//
//        List<String> stopTitels = Arrays.asList("senior");
//        FilesConfig config = Mockito.mock(FilesConfig.class);
//        Mockito.when(config.getStopTitles()).thenReturn(file);
//
//
//    }
//}
