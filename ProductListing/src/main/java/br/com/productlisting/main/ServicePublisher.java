package br.com.productlisting.main;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.productlisting.logger.LoggerManager;
import br.com.productlisting.service.Service;


/*
 * main class service
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-12-05
 */
public class ServicePublisher {

	private HttpServer server;
	private WebTarget target;
	public final URI BASE_URI = UriBuilder.fromUri("http://localhost/rest").port(8080).build();

	public void startService(){
		LoggerManager.info("Starting server: " + BASE_URI.getHost() +":" + BASE_URI.getPort());
		server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, new ResourceConfig(Service.class));
		target = ClientBuilder.newClient().target(BASE_URI).path("service");
	}

	public void seviceShutDown() {
		server.shutdown();
	}

	public WebTarget getTarget() {
		return this.target;
	}
	
	public static void main(String []args){
		
		LoggerManager.info("Starting server...");
		try{
			ServicePublisher service = new ServicePublisher();
			service.startService();
			LoggerManager.info("Server is started");
		    System.in.read();
		    LoggerManager.info("Shutting server down...");
		    service.seviceShutDown();
		}
		catch(Exception ex){
			LoggerManager.error(ex.getMessage());
		}
	}
}
