package com.e_koslowa.jobvacancyseeker.filter;

import com.e_koslowa.jobvacancyseeker.utils.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FileReaderTest {

    @Autowired
    FileReader reader;

    private static final String FILENAME = "test-stop-words.txt";

    @Test
    public void readerReadCorrect() {
        List<String> words = reader.readFile(FILENAME);
        Assertions.assertTrue(words.contains("10-200"));
        Assertions.assertTrue(words.contains("Senior"));
        Assertions.assertTrue(words.contains("front end"));
    }
}
