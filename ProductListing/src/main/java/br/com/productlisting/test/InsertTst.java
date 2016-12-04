package br.com.productlisting.test;

import java.util.Arrays;
import java.util.List;

import br.com.productlisting.entity.Image;
import br.com.productlisting.entity.Product;

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

		// Images
		Image genericCarImage = new Image();
		genericCarImage.setType("generic car image");
		
		Image sportCarImage = new Image();
		sportCarImage.setType("First sport car");
		
		Image sportCarImage2 = new Image();
		sportCarImage2.setType("Second sport car image");
		
		Image sportCarImage3 = new Image();
		sportCarImage3.setType("Third sport car image");

		// Products
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
		sportCar.setImages(Arrays.asList(sportCarImage));
		sportCarImage.setProduct(sportCar);

		// a Super sport car
		Product superSportCar = new Product();
		superSportCar.setName("Veyron");
		superSportCar.setDescription("a Bugatti super sport car");
		superSportCar.setParentProduct(sportCar);
		superSportCar.setImages(Arrays.asList(sportCarImage2, sportCarImage3));
		sportCarImage2.setProduct(superSportCar);
		sportCarImage3.setProduct(superSportCar);

		// a SUV car
		Product SUVCar = new Product();
		SUVCar.setName("Evoque");
		SUVCar.setDescription("a Jeep SUV car");
		SUVCar.setParentProduct(car);
		car.setImages(Arrays.asList(genericCarImage));

		// a truck car
		Product truckCar = new Product();
		truckCar.setName("VRaptor");
		truckCar.setDescription("a ford truck car");
		truckCar.setParentProduct(car);
		car.setImages(Arrays.asList(genericCarImage));

		saveProduct(car, sportCar, superSportCar, truckCar, SUVCar);

		List<Product> products = prodBO.getAllProductExcludingRelationships();
		assertEquals(products.size(), 5);

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
