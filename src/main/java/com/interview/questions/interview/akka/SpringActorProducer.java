package com.interview.questions.interview.akka;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * *
 * @author daviek19@gmail.com This class will always retrieve an actor instance
 * from the Spring’s ApplicationContext. As we’ve made the actor a
 * prototype-scoped bean, every call to the produce method will return a new
 * instance of the actor:
 */
public class SpringActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;

    private String beanActorName;

    public SpringActorProducer(ApplicationContext applicationContext, String beanActorName) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(beanActorName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }
}
