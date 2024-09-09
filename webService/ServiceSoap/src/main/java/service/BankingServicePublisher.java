package service;

import jakarta.xml.ws.Endpoint;

public class BankingServicePublisher {

	public static void main(String[] args) {
		
			Endpoint.publish("http://localhost:8080/banking", new BankAccountServiceImpl());
	        System.out.println("Service SOAP déployé sur http://localhost:8080/banking?wsdl");
	       

	}

}
