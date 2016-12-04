package br.com.productlisting.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.productlisting.bo.ProductBO;
import br.com.productlisting.connection.ConnectionFactory;
import br.com.productlisting.dao.ProductDAO;
import br.com.productlisting.entity.Product;
import br.com.productlisting.logger.LoggerManager;

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

	private ProductBO prodBO = new ProductBO(new ProductDAO(ConnectionFactory.getSessionFactory()));

	private Response getResponse(List<?> list){
		if(list != null)
			return Response.status(Response.Status.OK).entity(list).build();
		else
			return Response.status(Response.Status.NOT_FOUND).entity("Empty List").build();
	}
	
	private Response getResponse(Product p){
		if(p != null)
			return Response.status(Response.Status.OK).entity(p).build();
		else
			return Response.status(Response.Status.NOT_FOUND).entity("Empty List").build();
	}
	
	@GET
	@Path("/getAllProductExcludingRelationships")
    @Produces(value = {MediaType.APPLICATION_JSON})
	public Response  getAllProductExcludingRelationships(){
		LoggerManager.info("/getAllProductExcludingRelationships was called.");
		return getResponse(prodBO.getAllProductExcludingRelationships());
	}

	@GET
	@Path("/getAllProductIncludingParentRelationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
	public Response getAllProductIncludingParentRelationship(){
		LoggerManager.info("/getAllProductIncludingParentRelationship was called.");
		return getResponse(prodBO.getAllProductIncludingParentRelationship());
	}

	@GET
	@Path("/getAllProductIncludingParentAndImageRelationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
	public Response getAllProductIncludingParentAndImageRelationship(){
		LoggerManager.info("/getAllProductIncludingParentAndImageRelationship was called.");
		return getResponse(prodBO.getAllProductIncludingParentAndImageRelationship());

	}

	@GET
	@Path("/getAllProductIncludingImageRelationships")
    @Produces(value = {MediaType.APPLICATION_JSON})
	public Response getAllProductIncludingImageRelationships(){
		LoggerManager.info("/getAllProductIncludingImageRelationships was called.");
		return getResponse(prodBO.getAllProductIncludingImageRelationships());
	}

	@GET
	@Path("/getProductExcludingRelationships/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getProductExcludingRelationships(@PathParam("id")long id){
		LoggerManager.info("/getProductExcludingRelationships was called with Produc id " + id);
		return getResponse(prodBO.getProductExcludingRelationships(id));
	}

	@GET
	@Path("/getProductIncludingRelationships/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getProductIncludingRelationships(@PathParam("id") long id){
		LoggerManager.info("/getProductIncludingRelationships was called with Produc id " + id);
		return getResponse(prodBO.getProductIncludingRelationships(id));
	}

	@GET
	@Path("/getProductIncludingParentRelationship/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getProductIncludingParentRelationship(@PathParam("id")long id){
		LoggerManager.info("/getProductIncludingParentRelationship was called with Produc id " + id);
		return getResponse(prodBO.getProductIncludingParentRelationship(id));
	}

	@GET
	@Path("/getProductIncludingImageRelationship/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getProductIncludingImageRelationship(@PathParam("id")long id){
		LoggerManager.info("/getProductIncludingImageRelationship was called with Produc id " + id);
		return getResponse(prodBO.getProductIncludingImageRelationship(id));
	}
	
	
	@GET
	@Path("/getProductChildren/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getProductChildren(@PathParam("id")long id){
		LoggerManager.info("/getProductChilds was called with Produc id " + id);
		return getResponse(prodBO.getProductChildren(id));
	}

	@GET
	@Path("/getImages/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.TEXT_PLAIN})
	public Response getImages(@PathParam("id")long id){
		LoggerManager.info("/getProductChilds was called with Produc id " + id);
		return getResponse(prodBO.getImages(id));
	}
}
