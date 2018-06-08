package com.spring.config;

import java.util.Properties;
 
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring.model.UserDetails;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.spring" })
public class AppConfig {

	private Environment env;

	// @Bean
	// LocalContainerEntityManagerFactoryBean entityBean(DataSource datasource,
	// Environment env) {
	// LocalContainerEntityManagerFactoryBean bean = new
	// LocalContainerEntityManagerFactoryBean();
	// bean.setDataSource(datasource);
	// bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	// bean.setPackagesToScan("com.spring");
	//
	// Properties jpaProps = new Properties();
	// jpaProps.put("hibernate.dialect","org.hibernate.dialect.DB2Dialect");
	// jpaProps.put("hibernate.hbm2ddl.auto", "update");
	// jpaProps.put("hibernate.show_sql", "false");
	// return bean;
	//
	// }

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] { "com.spring" });
//		sessionFactory.setHibernateProperties(hibernateProperties());
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource); 
		builder.addAnnotatedClass(UserDetails.class);
		builder.scanPackages("com.spring");
		builder.addProperties(hibernateProperties());
		System.out.println("returning SessionFactory Object");
		return builder.buildSessionFactory();
	}

	// @Bean
	// JpaTransactionManager transactionManager(EntityManagerFactory emf) {
	// JpaTransactionManager transactionManager = new JpaTransactionManager();
	// transactionManager.setEntityManagerFactory(emf);
	// return transactionManager;
	// }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean(destroyMethod = "close")
	DataSource dataSource() {
//		HikariConfig dataSourceConfig = new HikariConfig();
		BasicDataSource source = new BasicDataSource();
		System.out.println("Environment : " + env);
		source.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		source.setUrl("jdbc:db2://172.22.33.37:50000/DIGILOC");
		source.setUsername("db2inst1");
		source.setPassword("Filenet@123#");

		return source;
	}

	@Bean
	public UserDetails getUser() {
		return new UserDetails();
	}

	public Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto","update");
		prop.put("hibernate.dialect","org.hibernate.dialect.DB2Dialect");
		//prop.put("hibernate.globally_quoted_identifiers","true");
		return prop;
	}

}
