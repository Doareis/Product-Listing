package br.com.avenuecode.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import junit.framework.TestCase;
import com.startrekrescue.control.Controller;

/*
 * tests service connection
 *
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
public class ServiceTest extends TestCase{

	public void test(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String planicie = target.path(
				"AvenueCodeTest/service/getAllProductExcludingRelationships").
				request().get(String.class);
		
		assertFalse(planicie != null);
		assertFalse(planicie.equals(""));
		
		
	}
	
}