package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.utils.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class BasicFilterTest {

    @InjectMocks
    BasicFilter filter;
    @Mock
    FileReader reader;

    @Test
    public void text_filter_return_false() {
        Mockito.when(reader.readFile(Mockito.anyString())).thenReturn(Arrays.asList("51-200", "1-10"));
        Assertions.assertFalse(filter.test("51-200 employees · IT Services and IT Consulting", "someFile"));
    }

    @Test
    public void text_filter_return_true() {
        Mockito.when(reader.readFile(Mockito.anyString())).thenReturn(Arrays.asList("51-200", "1-10"));
        Assertions.assertTrue(filter.test("201-500 employees · IT Services and IT Consulting", "someFile"));
    }
}
