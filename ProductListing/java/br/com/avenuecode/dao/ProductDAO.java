package br.com.avenuecode.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.avenuecode.entity.Product;
import br.com.avenuecode.logger.LoggerManager;

/**
 *
 * Class for data access
 * Related class: Product
 *
 *@author: Douglas Reis
 *@version: 1.0
 *@since 2016-11-23
 */
public class ProductDAO extends CommonDAO {

	public ProductDAO(SessionFactory sf) {
		super(sf);
	}

	/*
	 * Saves an product in the database
	 * 
	 * @param  product to be saved
	 */
	public void save(Product product){
		super.saveObject(product);
	}

	/*
	 * saves a product in the database
	 * 
	 * @param product to be saved
	 *
	 */
	public void saveProduct(Product product){

		try{
			session = sf.openSession();
			Transaction trans = session.beginTransaction();
			session.save(product);
			trans.commit();
		}
		catch(Throwable e){
			LoggerManager.error(e.getMessage());
		}
		finally{
			session.close();
		}
	}

	/*
	 * given id retrieves a product from the database
	 * 
	 * @param product id
	 * @param what clause
	 * @return product
	 */
	private Product getProduct(long id, String what){
		@SuppressWarnings("unchecked")
		List<Product> pList = (List<Product>) super.runQuery("select " + what + " from Product where id = " + id);
		if(pList.size() > 0)
			return pList.get(0);
		return null;
	}

	/*
	 * Get all products excluding relationships
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductExcludingRelationships(){
		return (List<Product>) super.runQuery("select p.name, p.description from Product p");
	}

	/*
	 * Get all products including parent relationship
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductIncludingParentRelationship(){
		return (List<Product>) super.runQuery(
				"select child.name, p.description, p.parentProduct from Product child");
	}

	/*
	 * Get all products including parent and image relationships
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductIncludingParentAndImageRelationship(){
		return (List<Product>) super.runQuery(
				"select p.name, p.description, p.parentProduct, i.type "
				+ "from Product p left join p.images i on p.id = i.product.id");

	}

	/*
	 * Get all products including image relationship
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductIncludingImageRelationships(){
		return (List<Product>) super.runQuery(
				"select p.name, p.description, i.type "
				+ "from Product p left join p.images i");
	}

	/*
	 * Gets product by id excluding parent relationship
	 * 
	 * @param  product id
	 * @return product list
	 */
	public Product getProductByIdExcludingRelationships(long id){
		return getProduct(id, "name, description");
	}

	/*
	 * Gets product by id including parent and image relationships
	 * 
	 * @param  product id
	 * @return product list
	 */
	public Product getProductByIdIncludingRelationships(long id){
		return getProduct(id, "name, description, parentProduct, images");
	}

	/*
	 * Gets product by id including parent relationship
	 * 
	 * @param  product id
	 * @return product list
	 */
	public Product getProductByIdIncludingParentRelationship(long id){
		return getProduct(id, "name, description, parentProduct");
	}

	/*
	 * Gets product by id including image relationship
	 * 
	 * @param  product id
	 * @return product list
	 */
	public Product getProductIncludingImageRelationship(long id){
		return getProduct(id, "name, description, images");
	}

	/*
	 * Gets list of childs by parent product id 
	 * 
	 * @param parent product id
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getProductChilds(long id){
		return (List<Product>) runQuery("from Product child  where parent_product_id = " + id);
	}
}
