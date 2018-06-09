package com.interview.questions.interview.soap.support;

import com.interview.questions.interview.common.soap.support.WeatherClientService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherTests {

    @Autowired
    private WeatherClientService weatherClientService;

    @Test
    public void testCelsiusToFahrenheitResponse() {
        assertEquals(weatherClientService.celsiusToFahrenheit("10"), "50");
    }
}
