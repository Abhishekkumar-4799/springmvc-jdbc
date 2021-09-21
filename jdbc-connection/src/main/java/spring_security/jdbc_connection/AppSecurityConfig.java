package spring_security.jdbc_connection;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity(debug=true)

public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	private Logger logger = Logger.getLogger(getClass().getName());
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		AppConfig appConfig = new AppConfig();
		logger.info("AppSecurityConfig" + "configure");
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.anyRequest().authenticated()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
		.and()
		.formLogin().loginPage("/loginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
		.and().logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	

}
