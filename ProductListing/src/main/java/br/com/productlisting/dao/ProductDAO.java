package br.com.productlisting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.productlisting.entity.Image;
import br.com.productlisting.entity.Product;
import br.com.productlisting.logger.LoggerManager;

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
				"select child.name, child.description, parent from Product child"
						+ " left join child.parentProduct parent");
	}

	/*
	 * Get all products including parent and image relationships
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductIncludingParentAndImageRelationship(){
		return (List<Product>) super.runQuery("from Product");
	}

	/*
	 * Get all products including image relationship
	 * 
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductIncludingImageRelationships(){
		return (List<Product>) super.runQuery(
				"select p.name, p.description, img as images from Product p "
						+ "left join p.images img");
	}

	/*
	 * Gets product by criteria
	 * 
	 * @param  criteria
	 * @return product 
	 */
	public Product getProductByCriteria(Criteria criteria){

		try{
			session = sf.openSession();

			@SuppressWarnings("unchecked")
			List<Product> result = criteria.list();
			if(result.size() == 1)
				return (Product) result.get(0);
			return null;
		}

		finally{
			session.close();
		}

	}
	
	/*
	 * Gets product's columns in generic list of object[]
	 * 
	 * @param  query and id
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getProductColumns(String sQuery, long id){
		
		try{
			session = sf.openSession();
			Query q = session.createQuery(sQuery).setParameter("id", id);
			return q.list();
		}

		finally{
			session.close();
		}
		
	}

	/*
	 * Gets product by id excluding parent relationship
	 * 
	 * @param  product id
	 * @return product 
	 */
	public Product getProductByIdExcludingRelationships(long id){

		try{
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class)
					.setProjection(Projections.projectionList()
							.add(Projections.property("id"), "id")
							.add(Projections.property("name"), "name")
							.add(Projections.property("description"), "description"))
					.add(Restrictions.eq("id", id))
					.setResultTransformer(Transformers.aliasToBean(Product.class));

			return getProductByCriteria(criteria);
		}
		catch(Exception e){
			LoggerManager.error(String.format(
					"[getProductByIdExcludingRelationships] Could not retrieve product for id: %d, \n message: %s", (int) id, e.getMessage()));
		}
		return null;

	}

	/*
	 * Gets product by id including parent and image relationships
	 * 
	 * @param  product id
	 * @return product 
	 */
	public Product getProductByIdIncludingRelationships(long id){
		try{
			session = sf.openSession();
			Query q = session.createQuery("from Product p where p.id = :id");
			q.setLong("id", id);
			return (Product) q.uniqueResult();
		}
		catch(Exception e){
			LoggerManager.error(String.format(
					"[getProductByIdIncludingRelationships] Could not retrieve product for id: %d, \n message: %s", (int) id, e.getMessage()));
		}
		finally{
			session.close();
		}
		return null;
	}

	/*
	 * Gets product by id including parent relationship
	 * 
	 * @param  product id
	 * @return product 
	 */
	public Product getProductByIdIncludingParentRelationship(long id){
		try{
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class)
					.setProjection(Projections.projectionList()
							.add(Projections.property("id"), "id")
							.add(Projections.property("name"), "name")
							.add(Projections.property("description"), "description")
							.add(Projections.property("parentProduct"), "parentProduct"))
					.add(Restrictions.eq("id", id))
					.setResultTransformer(Transformers.aliasToBean(Product.class));

			return getProductByCriteria(criteria);
		}
		catch(Exception e){
			LoggerManager.error(String.format(
					"[getProductByIdIncludingParentRelationship] Could not retrieve product for id: %d, \n message: %s", (int) id, e.getMessage()));
		}
		return null;
	}

	/*
	 * Gets product by id including image relationship
	 * 
	 * @param  product id
	 * @return product 
	 */
	public Product getProductIncludingImageRelationship(long id){		
		try{
			session = sf.openSession();
			List<Object[]> rows = getProductColumns(
					"select p.description, img from Product p left join p.images img where p.id = :id", id);
			
			if(rows.size() > 0){
				Product result = new Product();
				result.setId(id);
				result.setDescription((String) rows.get(0)[0]);
				for(Object row[] : rows){
					result.getImages().add((Image) row[1]);
				}
				return result;
			}
			else
				return null;
			
		}
		catch(Exception e){
			LoggerManager.error(String.format(
					"[getProductIncludingImageRelationship] Could not retrieve product for id: %d, \n message: %s", (int) id, e.getMessage()));
		}
		return null;
	}

	/*
	 * Gets list of childs by parent product id 
	 * 
	 * @param parent product id
	 * @return product list
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getProductChildren(long id){
		return (List<Product>) runQuery("from Product child  where parent_product_id = " + id);
	}

	@SuppressWarnings("unchecked")
	public List<Image> getImages(long id) {
		return (List<Image>) runQuery("select p.images from Product p where  p.id = " + id);
	}
	
}
