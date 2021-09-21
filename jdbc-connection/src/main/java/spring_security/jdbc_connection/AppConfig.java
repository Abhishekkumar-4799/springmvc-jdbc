package spring_security.jdbc_connection;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@ComponentScan(basePackages="spring_security.jdbc_connection")
@Configuration
@PropertySource("classpath:persistance-mysql.properties")
@EnableWebMvc
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
	public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        resolver.setPrefix("WEB-INF/view");
        return resolver;
    }
    
    // define a bean for data source
    
    @Bean
    public DataSource dataSource() {
    	logger.info("AppConfig" + "DataSource");
    	ComboPooledDataSource dataSource = new ComboPooledDataSource();
    	logger.info(">>>>>>>>>>>>>> jdbcdriver" + env.getProperty("jdbc.driver") + "<<<<<<<<<<<<<<<<<<<<<<");
    	try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.info(">>>>>>>>>>>>>> jdbcdriver" + env.getProperty("jdbc.url") + "<<<<<<<<<<<<<<<<<<<<<<");
    	logger.info(">>>>>>>>>>>>>> jdbcuser" + env.getProperty("jdbc.user") + "<<<<<<<<<<<<<<<<<<<<<<");
    	
    	dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
    	dataSource.setUser(env.getProperty("jdbc.user"));
    	dataSource.setPassword(env.getProperty("jdbc.password"));
    	
    	dataSource.setInitialPoolSize(getInt("connection.pool.initialPoolSize"));
    	dataSource.setInitialPoolSize(getInt("connection.pool.minPoolSize"));
    	dataSource.setInitialPoolSize(getInt("connection.pool.maxPoolSize"));
    	dataSource.setInitialPoolSize(getInt("connection.pool.maxIdleTime"));
    	
    	logger.info(">>>>>>>>>>>>>> jdbc property" + env.getProperty("connection.pool.initialPoolSize") + "<<<<<<<<<<<<<<<<<<<<<<");
    	logger.info(">>>>>>>>>>>>>> jdbc property" + env.getProperty("connection.pool.maxIdleTime") + "<<<<<<<<<<<<<<<<<<<<<<");
    	
    	return dataSource;
    }
    
    private int getInt(String propName) {
    	String propVal = env.getProperty(propName);
    	return Integer.parseInt(propVal);
    }
}
