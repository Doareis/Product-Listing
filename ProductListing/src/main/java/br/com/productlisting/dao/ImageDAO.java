package br.com.productlisting.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import br.com.productlisting.entity.Image;

/**
*
* Class for data access
* Related class: Image
*
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
public class ImageDAO extends CommonDAO{

	public ImageDAO(SessionFactory sf) {
		super(sf);
	}

	/*
	* Saves an image in the database
	* 
	* @param  image to be saved
	*/
	public void save(Image image){
		super.saveObject(image);
	}
	
/*
	* Gets a list of images that are related to the product id
	* 
		* @param  product id
	* @return image list
	*/
	@SuppressWarnings("unchecked")
	public List<Image> getProductImages(long id){
		return (List<Image>) super.runQuery("from Image i where product_id = " + id);
	}
}
