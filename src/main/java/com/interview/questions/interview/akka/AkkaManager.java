package com.interview.questions.interview.akka;

import akka.actor.ActorSystem;
import static com.interview.questions.interview.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AkkaManager {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AkkaConfig akkaConfig;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create(akkaConfig.getActorSystemName());
        SPRING_EXTENSION_PROVIDER.get(system)
                .initialize(applicationContext);
        return system;
    }
}
