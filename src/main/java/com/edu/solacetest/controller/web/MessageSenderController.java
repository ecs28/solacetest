package com.edu.solacetest.controller.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.solacetest.controller.jms.SolaceMessageProducer;

@RestController
public class MessageSenderController {

	@Autowired
	protected SolaceMessageProducer sender;
	
	//mapping root method	
	@RequestMapping(value = "/send/{message}")
	public String index(@PathVariable String message) throws Exception {
		sender.send(message);
		return "Message sent : " + LocalDateTime.now();
	}
}
