package springboot.app.configuration;

import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hibernate.properties")
@ConfigurationProperties(prefix="hibernate")
public class HibernateConfiguration {
	
	@NotNull
	private String dialect;
	@NotNull
	private String autocommit;
	@NotNull
	private String showSql;
	@NotNull
	private String hbm2ddl_auto;
	
	@NotNull
	private String entityLocation;
	
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public String getAutocommit() {
		return autocommit;
	}
	public void setAutocommit(String autocommit) {
		this.autocommit = autocommit;
	}
	public String getShowSql() {
		return showSql;
	}
	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}
	public String getHbm2ddl_auto() {
		return hbm2ddl_auto;
	}
	public void setHbm2ddl_auto(String hbm2ddl_auto) {
		this.hbm2ddl_auto = hbm2ddl_auto;
	}
	
	
	public String getEntityLocation() {
		return entityLocation;
	}
	public void setEntityLocation(String entityLocation) {
		this.entityLocation = entityLocation;
	}
	
	@Bean("hibernateProperties")
	public Properties getHibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", dialect);
		hibernateProperties.setProperty("hibernate.show_sql", showSql);
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl_auto);
		hibernateProperties.setProperty("hibernate.connection.autocommit", autocommit);
		return hibernateProperties;
		
	}

}
