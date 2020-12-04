package org.apache.camel.example;

import org.eclipse.jetty.server.handler.ContextHandler;


public class CustomContextHandler extends ContextHandler{
	CustomContextHandler(){
		CustomErrorHandler errorHandler = new CustomErrorHandler();
		setErrorHandler(errorHandler);
	}
}
