package br.com.avenuecode.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import br.com.avenuecode.bo.ProductBO;
import br.com.avenuecode.connection.ConnectionFactory;
import br.com.avenuecode.connection.DataSourceConnection;
import br.com.avenuecode.dao.ProductDAO;
import br.com.avenuecode.entity.Product;
import br.com.avenuecode.logger.LoggerManager;

/*
 *
 * Service main class
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
@Path("/service")
public class Service {

	private ProductBO prodBO;

	@PostConstruct
	public void init(){
		prodBO = new ProductBO(new ProductDAO(ConnectionFactory.getSessionFactory()));
		DataSourceConnection.createSchema();
	}

	@GET
	@Path("/getAllProductExcludingRelationships")
    @Produces(MediaType.APPLICATION_JSON) 
	public JSONArray  getAllProductExcludingRelationships(){
		LoggerManager.info("/getAllProductExcludingRelationships was called.");
		return new JSONArray(prodBO.getAllProductExcludingRelationships());
	}

	@GET
	@Path("/getAllProductIncludingParentRelationship")
	@Produces(MediaType.APPLICATION_JSON) 
	public JSONArray getAllProductIncludingParentRelationship(){
		LoggerManager.info("/getAllProductIncludingParentRelationship was called.");
		return new JSONArray(prodBO.getAllProductIncludingParentRelationship());
	}

	@GET
	@Path("/getAllProductIncludingParentAndImageRelationship")
	@Produces("application/json; charset=UTF-8")
	public JSONArray getAllProductIncludingParentAndImageRelationship(){
		LoggerManager.info("/getAllProductIncludingParentAndImageRelationship was called.");
		return new JSONArray(prodBO.getAllProductIncludingParentAndImageRelationship());

	}

	@GET
	@Path("/getAllProductIncludingImageRelationships")
	@Produces("application/json; charset=UTF-8")
	public JSONArray getAllProductIncludingImageRelationships(){
		LoggerManager.info("/getAllProductIncludingImageRelationships was called.");
		return new JSONArray(prodBO.getAllProductIncludingImageRelationships());
	}

	@GET
	@Path("/getProductExcludingRelationships")
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	public Product getProductExcludingRelationships(long id){
		LoggerManager.info("/getProductExcludingRelationships was called with Produc id " + id);
		return prodBO.getProductExcludingRelationships(id);
	}

	@GET
	@Path("/getProductIncludingRelationships")
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	public Product getProductIncludingRelationships(long id){
		LoggerManager.info("/getProductIncludingRelationships was called with Produc id " + id);
		return prodBO.getProductIncludingRelationships(id);
	}

	@GET
	@Path("/getProductIncludingParentRelationship")
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	public Product getProductIncludingParentRelationship(long id){
		LoggerManager.info("/getProductIncludingParentRelationship was called with Produc id " + id);
		return prodBO.getProductIncludingParentRelationship(id);
	}

	@GET
	@Path("/getProductIncludingImageRelationship")
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	public Product getProductIncludingImageRelationship(long id){
		LoggerManager.info("/getProductIncludingImageRelationship was called with Produc id " + id);
		return prodBO.getProductIncludingImageRelationship(id);
	}

}
