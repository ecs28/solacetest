package com.edu.solacetest.controller.jms;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;


@Component
public class SolaceMessageConsumer {
	
	  private static final Logger logger = LoggerFactory.getLogger(SolaceMessageConsumer.class);

      // Retrieve the name of the queue from the application.properties file
      @JmsListener(destination = "${solace.jms.demoQueueName}")
      public void processMsg(Message<String> msg) {
      	StringBuffer msgAsStr = new StringBuffer("============= Received \nHeaders:");
      	MessageHeaders hdrs = msg.getHeaders();
      	msgAsStr.append("\nUUID: "+hdrs.getId());
      	msgAsStr.append("\nTimestamp: "+hdrs.getTimestamp());
      	Iterator<String> keyIter = hdrs.keySet().iterator();
      	while (keyIter.hasNext()) {
      		String key = keyIter.next();
          	msgAsStr.append("\n"+key+": "+hdrs.get(key));
      	}
      	msgAsStr.append("\nPayload: "+msg.getPayload());
          logger.info(msgAsStr.toString());
          logger.info("=============");
      }
	

}
