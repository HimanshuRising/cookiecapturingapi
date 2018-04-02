package springboot.app.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource("classpath:oracle.properties")
@ConfigurationProperties(prefix="oracle")
public class OracleConfiguration {
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String url;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Bean
	DataSource oracleDataSource() throws SQLException{
		OracleDataSource oracleDataSource = new OracleDataSource();
		oracleDataSource.setUser(username);
		oracleDataSource.setPassword(password);
		oracleDataSource.setURL(url);
		return oracleDataSource;
		
	}
	
	

}
