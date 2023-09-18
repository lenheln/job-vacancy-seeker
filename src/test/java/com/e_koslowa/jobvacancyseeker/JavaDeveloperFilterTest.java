//package com.e_koslowa.jobvacancyseeker;
//
//import com.e_koslowa.jobvacancyseeker.filter.JavaDeveloperFilter;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class JavaDeveloperFilterTest {
//
//    @Autowired
//    JavaDeveloperFilter filter;
//
//    @Test
//    void javaDeveloperTestDontPassForbiddenName() {
//
//        JobVacancy job = new JobVacancy(
//                " Senior developer",
//                "Google",
//                "");
//
//        Assertions.assertFalse(filter.test(job));
//    }
//
//    @Test
//    void javaDeveloperTestDontPassForbiddenDescription() {
//
//        JobVacancy job = new JobVacancy(
//                " Java developer",
//                "Google",
//                "Sehr gute Deutsch (min C1 Level) und/oder Englisch");
//
//        Assertions.assertFalse(filter.test(job));
//    }
//
//    @Test
//    void javaDeveloperTestPassAllowedDescription() {
//
//        JobVacancy job = new JobVacancy(
//                " Java developer",
//                "Google",
//                "Just a good job");
//
//        Assertions.assertTrue(filter.test(job));
//    }
//}
