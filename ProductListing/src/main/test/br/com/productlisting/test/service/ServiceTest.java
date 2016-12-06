package br.com.productlisting.test.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.productlisting.logger.LoggerManager;
import br.com.productlisting.main.ServicePublisher;

/*
 * tests service 
 * run simple tests
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
public class ServiceTest {
	
	private ServicePublisher publisher;
	
	private List<String> pathList(){
		return Arrays.asList(
				"getAllProductExcludingRelationships", "getAllProductIncludingParentRelationship", "getAllProductIncludingParentAndImageRelationship",
				"getAllProductIncludingImageRelationships", "getProductExcludingRelationships/1", "getProductIncludingRelationships/1",
				"getProductIncludingParentRelationship/1", "getProductIncludingImageRelationship/1",  "getProductChildren/1", "getImages/1");
	}
	@Before
	public void setUp() throws Exception  {
		publisher = new ServicePublisher();
		publisher.startService();
	}

	@Test
	public void testService(){
		LoggerManager.info("Starting tests");

		int count = 0;
		try{
			List<String> paths = pathList();
			for(String path : paths){
				String response = null;
				response = publisher.getTarget().path(path).request().accept(MediaType.APPLICATION_JSON).get(String.class);
			
				// tests response
				assertTrue(response != null);
				count++;
			}
		}
		catch(Exception e){
			LoggerManager.error("Errors on tests: " + e.getMessage());

		}
		assertEquals(pathList().size(), count + 1);
		LoggerManager.info(String.format("Tests executed successfully. %d methods were tested", count));

	}

	@After
	public void tearDown(){
		LoggerManager.info("Shutting down server");
		publisher.seviceShutDown();
	}
}