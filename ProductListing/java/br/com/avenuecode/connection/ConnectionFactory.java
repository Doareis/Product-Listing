package br.com.avenuecode.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.avenuecode.logger.LoggerManager;

/*
*@Author: Douglas Reis
*@Version: 1.0
*@Since 2016-11-23
*/
public class ConnectionFactory {

	private static final SessionFactory sessionFctry = buildSessionFactory("hibernate.cfg.xml");
	private static final SessionFactory tstSessionFctry = buildSessionFactory("hibernate.mysql.cfg.xml");

	private static SessionFactory buildSessionFactory(String configFile) {
		try{
			Configuration cfg = new Configuration();
			cfg.configure(configFile);
            return cfg.buildSessionFactory();
		}		
		catch(Exception e){
			LoggerManager.error("Could not initialize connection: " + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFctry;
	}
	
	public static SessionFactory getTestSessionFactory() {
		return tstSessionFctry;
	}
}
