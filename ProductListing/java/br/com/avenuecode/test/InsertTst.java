package br.com.avenuecode.test;

import java.util.Arrays;
import java.util.List;

import br.com.avenuecode.entity.Image;
import br.com.avenuecode.entity.Product;

/*
 *
 * class for insert testing
 *
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
public class InsertTst extends Test{

	public void test(){

		Image genericCarImage = new Image();
		genericCarImage.setType("generic car image");

		Image sportCarImage = new Image();
		sportCarImage.setType("First sport car");
		
		Image sportCarImage2 = new Image();
		sportCarImage2.setType("Second sport car image");

		// a simple car that is the parent of all others
		Product car = new Product();
		car.setName("car");
		car.setDescription("a simple car");
		car.setImages(Arrays.asList(genericCarImage));
		genericCarImage.setProduct(car);

		// a sport car
		Product sportCar = new Product();
		sportCar.setName("Mustang");
		sportCar.setDescription("a ford sport car");
		sportCar.setParentProduct(car);
		sportCar.setImages(Arrays.asList(sportCarImage, sportCarImage2));
		sportCarImage.setProduct(sportCar);
		sportCarImage2.setProduct(sportCar);

		saveProduct(car, sportCar);

		List<Product> products = prodBO.getAllProductExcludingRelationships();
		assertEquals(products.size(), 2);

	}
	/*
	 *
	 * saves a list of products in the database
	 *
	 * @param product list
	 *
	 **/
	private void saveProduct(Product ... productArgs){
		for(Product p : productArgs){
			prodBO.saveProduct(p);
		}
	}

}
