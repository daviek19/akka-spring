package com.interview.questions.interview.common.soap.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tempconvert.wsdl.CelsiusToFahrenheit;
import tempconvert.wsdl.CelsiusToFahrenheitResponse;

/**
 * A soap client that provides abstraction to the temperature API
 */
public class WeatherClientService extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClientService.class);

    @Value("${temparature_soap_action}")
    private String soapAction;
    @Value("${temparature_payload_url}")
    public String payLoadUrl;

    /**
     * Converts Celsius to Fahrenheit
     *
     * @param celsius
     * @return
     */
    public String celsiusToFahrenheit(String celsius) {
        LOGGER.info("celsiusToFahrenheit received {}", celsius);

        String fahrenheit = "";
        CelsiusToFahrenheitResponse response;
        SoapActionCallback callBackUrl = new SoapActionCallback(soapAction);

        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius(celsius);

        try {
            response = (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(payLoadUrl, request, callBackUrl);
            fahrenheit = response.getCelsiusToFahrenheitResult();
        } catch (Exception e) {
            LOGGER.error("celsiusToFahrenheit", e);
        }

        LOGGER.info("converted {} celsius to {} fahrenheit", celsius, fahrenheit);
        return fahrenheit;
    }
}
