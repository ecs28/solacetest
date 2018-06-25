package com.edu.solacetest.controller.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.solace.services.core.model.SolaceServiceCredentials;
import com.solace.spring.cloud.core.SolaceMessagingInfo;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SpringSolJmsConnectionFactoryCloudFactory;

@Component
public class SolaceMessageProducer {

	 private static final Logger logger = LoggerFactory.getLogger(SolaceMessageProducer.class);

     @Autowired
     private JmsTemplate jmsTemplate;

     // Other beans that can be used together to create a customized JmsTemplate
     @SuppressWarnings("unused")
	 @Autowired private SolConnectionFactory solConnectionFactory;
     @SuppressWarnings("unused")
     @Autowired private SpringSolJmsConnectionFactoryCloudFactory springSolJmsConnectionFactoryCloudFactory;
     @SuppressWarnings("unused")
     @Autowired(required=false) private SolaceServiceCredentials solaceServiceCredentials;

     /*
     For backwards compatibility:
     - As before, these exist only in the specific scenario where the app is deployed in Cloud Foundry.*/
     @Autowired(required=false) private SolaceMessagingInfo solaceMessagingInfo;

     @Value("${solace.jms.demoQueueName}")
     private String queueName;

     public void send(String msg) throws Exception {
         logger.info("============= Sending " + msg);
         this.jmsTemplate.convertAndSend(queueName, msg);
         logger.info("============= ");
     }

	
	
}
