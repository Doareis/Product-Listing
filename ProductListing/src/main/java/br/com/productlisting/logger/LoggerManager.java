package br.com.productlisting.logger;

import org.apache.log4j.Logger;

/*
 *
 * Class for logging
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
public class LoggerManager {

	static private final Logger logger = Logger.getLogger("LoggerManager");


	/*
	 * @param log error message
	 *
	 **/
	public static void error(String msg){
		logger.error(msg);
	}

	/*
	 * @param log info message
	 *
	 **/
	public static void info(String msg){
		logger.info(msg);
	}
}
