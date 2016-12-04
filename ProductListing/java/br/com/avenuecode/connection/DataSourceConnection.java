package br.com.avenuecode.connection;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DataSourceConnection {

	static public void createSchema(){
		DataSource dataSource = createDataSource();
		DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
	}

	private static DatabasePopulator createDatabasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		databasePopulator.addScript(new ClassPathResource("schema.sql"));
		return databasePopulator;
	}

	private static SimpleDriverDataSource createDataSource() {
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setDriverClass(org.hsqldb.jdbcDriver.class);
		simpleDriverDataSource.setUrl("jdbc:hsqldb:ACProductDB;AUTO_RECONNECT=TRUE");
		simpleDriverDataSource.setUsername("sa");
		simpleDriverDataSource.setPassword("");
		return simpleDriverDataSource;      
	}
}
