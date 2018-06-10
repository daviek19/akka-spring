package com.interview.questions.interview.common.csv.support;

import com.interview.questions.interview.InterviewApplication;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * https://commons.apache.org/proper/commons-csv/user-guide.html
 *
 * @author USER
 */
@Service
public class CsvTest {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InterviewApplication.class);

    public void createCSVFile() throws IOException {

        Map<String, String> AUTHOR_BOOK_MAP = new HashMap<String, String>() {
            {
                put("Dan Simmons", "Hyperion");
                put("Douglas Adams", "The Hitchhiker's Guide to the Galaxy");
            }
        };

        String[] HEADERS = {"author", "title"};

        FileWriter out = new FileWriter("fileoutput/book_new.csv");

        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS));

        try {
            AUTHOR_BOOK_MAP.forEach((author, title) -> {
                try {
                    LOGGER.info("writing the author {} and title {}", author, title);
                    printer.printRecord(author, title);
                } catch (IOException ex) {
                    Logger.getLogger(CsvTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            printer.flush();
        } catch (IOException ex) {
            Logger.getLogger(CsvTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
