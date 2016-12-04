package br.com.productlisting.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.productlisting.logger.LoggerManager;

/**
 *
 * Abstract class for common methods realted to data access
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
abstract class CommonDAO{

	SessionFactory sf = null;
	Session session = null;

	public CommonDAO(SessionFactory sf){
		this.sf = sf;
	}

	/*
	 * gets a list of objects related to the input query
	 * @param query
	 * @returns Object list
	 *
	 */
	protected List<?> runQuery(String query){

		try{
			session = sf.openSession();
			return session.createQuery(query).list();
		}
		catch(Exception e){
			LoggerManager.error("Could not run query: " + query + e.getMessage());
		}
		finally{
			session.close();
		}
		return null;
	}

	/*
	 * Saves an object in the database
	 * 
	 * @param  the object to be saved
	 */
	public void saveObject(Object obj){

		try{
			session = sf.openSession();
			Transaction trans = session.beginTransaction();
			session.save(obj);
			trans.commit();
		}
		catch(Exception e){
			LoggerManager.error("Could not persist object: " + obj.getClass().getName() + e.getMessage());
		}
		finally{
			session.close();
		}
	}

}