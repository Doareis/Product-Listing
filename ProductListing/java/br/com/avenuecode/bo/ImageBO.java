package br.com.avenuecode.bo;

import java.util.List;

import br.com.avenuecode.dao.ImageDAO;
import br.com.avenuecode.entity.Image;

/**
*
* Business object class 
* Related class: Image
*
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
public class ImageBO {

	// TODO: usar spring
	private ImageDAO dao;
	
	public ImageBO(ImageDAO dao){
		this.dao = dao;
	}
	
	public void saveImage(Image img){
		dao.save(img);
	}

	/*
	* gets images for specific product id
	* 
	* @return list of image
	* @param product id
	*/ 
	public List<Image> getProductImages(long prodId){
		return dao.getProductImages(prodId);
	}
}
