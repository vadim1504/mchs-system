package by.mchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@EnableJms
@SpringBootApplication
public class JMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(JMSApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(JMSApplication.class, args);
//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
//        while (true) {
//            jmsTemplate.convertAndSend("OrderTransactionQueue", new Message(1, 1, "a", "b", "1", 1, new Time(10000), 5000));
//            jmsTemplate.convertAndSend("OrderTransactionQueue", new Message(2, 1, "a", "b", "1", 1, new Time(10000), 1000));
//            jmsTemplate.convertAndSend("OrderTransactionQueue", new Message(3, 1, "a", "b", "1", 1, new Time(10000), 3000));
//        }
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
