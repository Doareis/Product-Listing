package br.com.avenuecode.test;

import br.com.avenuecode.bo.ImageBO;
import br.com.avenuecode.bo.ProductBO;
import br.com.avenuecode.connection.ConnectionFactory;
import br.com.avenuecode.dao.ImageDAO;
import br.com.avenuecode.dao.ProductDAO;
import junit.framework.TestCase;

/*
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
abstract public class Test extends TestCase{

	protected ImageBO imgBO = new ImageBO(new ImageDAO(ConnectionFactory.getTestSessionFactory()));
	protected ProductBO prodBO = new ProductBO(new ProductDAO(ConnectionFactory.getTestSessionFactory()));
	
}
