package br.com.avenuecode.test;

import java.util.List;

import br.com.avenuecode.entity.Product;

public class SelectTst extends Test{

	public void test(){
		
		List<Product> prodList = prodBO.getAllProductExcludingRelationships();
		checkProductListExcludingRelationships(prodList);
		
	}
	private void checkProductListExcludingRelationships(List<Product> prodList) {
		for(Product product : prodList){
			assertNull(product.getParentProduct());
			//assertNull(product.getImageList());
		}
	}
}
