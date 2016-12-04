package br.com.productlisting.test;

import java.util.List;

import br.com.productlisting.entity.Product;

public class SelectTst extends Test{

	public void test(){
		List<Product> prodList = prodBO.getAllProductIncludingParentRelationship();
		checkProductListExcludingRelationships(prodList);
	}
	
	private void checkProductListExcludingRelationships(List<Product> prodList) {
		for(Product product : prodList){
			assertNull(product.getParentProduct());
			assertNull(product.getImages());
		}
	}
}
