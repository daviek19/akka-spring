package com.interview.questions.interview;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import static com.interview.questions.interview.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;
import com.interview.questions.interview.akka.actors.CelsiusToFahrenheit;
import com.interview.questions.interview.common.csv.support.CsvTest;
import com.interview.questions.interview.common.pdf.support.PdfTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InterviewApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewApplication.class);

    @Autowired
    private ActorSystem system;

    @Autowired
    private PdfTest pdfTest;
    
    @Autowired
    private CsvTest csvTest;

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            pdfTest.testPdfTable();
            
            csvTest.createCSVFile();

//            ActorRef celsiusToFahrenheitActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system)
//                    .props("CelsiusToFahrenheit"), "celsius_to_fahrenheit_actor");

//            for (int i = 0; i < 10; i++) {
//                celsiusToFahrenheitActor.tell(String.valueOf(i), ActorRef.noSender());
//            }

        };
    }
}
