package br.com.productlisting.test;

import junit.framework.TestCase;
import br.com.productlisting.bo.ImageBO;
import br.com.productlisting.bo.ProductBO;
import br.com.productlisting.connection.ConnectionFactory;
import br.com.productlisting.dao.ImageDAO;
import br.com.productlisting.dao.ProductDAO;

/*
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
abstract public class Test extends TestCase{

	protected ImageBO imgBO = new ImageBO(new ImageDAO(ConnectionFactory.getSessionFactory()));
	protected ProductBO prodBO = new ProductBO(new ProductDAO(ConnectionFactory.getSessionFactory()));
	
}
