package com.interview.questions.interview.akka.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import com.interview.questions.interview.common.soap.support.WeatherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("CelsiusToFahrenheit")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CelsiusToFahrenheit extends AbstractLoggingActor {

    @Autowired
    private WeatherClientService weatherClientService;

    static public Props props() {
        return Props.create(CelsiusToFahrenheit.class,
                () -> new CelsiusToFahrenheit()
        ).withDispatcher("blocking-io-dispatcher");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, (x) -> {
            weatherClientService.celsiusToFahrenheit(x);
        }).build();
    }

}
