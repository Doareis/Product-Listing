package br.com.avenuecode.bo;

import java.util.List;

import br.com.avenuecode.dao.ProductDAO;
import br.com.avenuecode.entity.Product;

/**
 *
 * Business object class 
 * Related class: Product
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
public class ProductBO {

	private ProductDAO dao;

	public ProductBO(ProductDAO dao){
		this.dao = dao;
	}

	public void saveProduct(Product product){
		dao.saveProduct(product);
	}

	public List<Product> getAllProductExcludingRelationships(){
		return dao.getAllProductExcludingRelationships();
	}

	public List<Product> getAllProductIncludingParentRelationship(){
		return dao.getAllProductIncludingParentRelationship();
	}
	public List<Product> getAllProductIncludingParentAndImageRelationship(){
		return dao.getAllProductIncludingParentAndImageRelationship();

	}
	public List<Product> getAllProductIncludingImageRelationships(){
		return dao.getAllProductIncludingImageRelationships();
	}

	public Product getProductExcludingRelationships(long id){
		return dao.getProductByIdExcludingRelationships(id);
	}

	public Product getProductIncludingRelationships(long id){
		return dao.getProductByIdIncludingRelationships(id);
	}

	public Product getProductIncludingParentRelationship(long id){
		return dao.getProductByIdIncludingParentRelationship(id);
	}

	public Product getProductIncludingImageRelationship(long id){
		return dao.getProductIncludingImageRelationship(id);
	}

	public List<Product> getProductChilds(long id){
		return dao.getProductChilds(id);
	}

}
