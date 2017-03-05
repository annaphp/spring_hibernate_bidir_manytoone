package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages={"repo"})
@Configuration
public class PersistenceConfig {
	
	@Bean 
	public DataSource dataSource() {  
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/practice");
		ds.setUsername("root");
	    ds.setPassword("root");	 
	    ds.setInitialSize(5);
	    ds.setMaxActive(10);
		return ds;  
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setPackagesToScan(new String[] {"model"});
		Properties props = new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.hbm2ddl.auto", "create");
	//	props.setProperty("hibernate.show_sql", "true");
		sfb.setHibernateProperties(props);
		return sfb;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
	{
	    HibernateTransactionManager htm = new HibernateTransactionManager();
	    htm.setSessionFactory(sessionFactory);
	    return htm;
	}

}
