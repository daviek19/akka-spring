package com.interview.questions.interview.akka;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import com.interview.questions.interview.akka.SpringExtension.SpringExt;
import org.springframework.context.ApplicationContext;

/**
 * This class makes using external dependency injection frameworks a fairly easy task.
 * @author daviek19@gmail.com
 */
public class SpringExtension extends AbstractExtensionId<SpringExt> {

    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
         return new SpringExt();
    }

    public static class SpringExt implements Extension {

        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
        }

    }

}
