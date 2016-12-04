package br.com.productlisting.test.service;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.productlisting.logger.LoggerManager;
import br.com.productlisting.service.Service;

/*
 * tests service connection
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
public class ServiceTest {
	
	private final URI BASE_URI = UriBuilder.fromUri("http://localhost/rest").port(8080).build();

	private HttpServer server;
	private WebTarget target;
	
	private List<String> pathList(){
		return Arrays.asList(
				"getAllProductExcludingRelationships", "getAllProductIncludingParentRelationship", "getAllProductIncludingParentAndImageRelationship",
				"getAllProductIncludingImageRelationships", "getProductExcludingRelationships/1", "getProductIncludingRelationships/1",
				"getProductIncludingParentRelationship/1", "getProductIncludingImageRelationship/1",  "getProductChildren/1", "getImages/1");
	}
	@Before
	public void init() throws Exception  {
		LoggerManager.info("Starting server: " + BASE_URI.getHost() +":" + BASE_URI.getPort());
		server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, new ResourceConfig(Service.class));
		target = ClientBuilder.newClient().target(BASE_URI).path("service");
	}

	@Test
	public void serviceTest(){
		LoggerManager.info("Starting tests");

		int count = 0;
		try{
			List<String> paths = pathList();
			for(String path : paths){
				String response = null;
				response = target.path(path).request().accept(MediaType.APPLICATION_JSON).get(String.class);
			
				// tests response
				assertTrue(response != null);
				count++;
			}
		}
		catch(Exception e){
			LoggerManager.error("Errors on tests: " + e.getMessage());

		}
		
		LoggerManager.info(String.format("Tests executed successfully. %d methods were tested", count));

	}

	@After
	public void shutDown(){
		LoggerManager.info("Shutting down server");
		server.shutdown();
	}
}