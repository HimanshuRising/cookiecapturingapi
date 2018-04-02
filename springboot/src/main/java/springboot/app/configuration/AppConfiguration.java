package springboot.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({OracleConfiguration.class,HibernateConfiguration.class})
@EnableJpaRepositories(basePackages="springboot.app.repository")
@EnableTransactionManagement
@ComponentScan("springboot.app")
@EntityScan("springboot.app.domains")
public class AppConfiguration {

	
	@Autowired
	private DataSource oracleDataSource;	
	
	@Autowired
	@Qualifier("hibernateProperties")
	private Properties hibernateProperties;
	
	
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
		hibernateAdapter.setGenerateDdl(true);
		hibernateAdapter.setShowSql(true);
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter);
        entityManagerFactoryBean.setDataSource(oracleDataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("springboot.app.domains");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties);
        return entityManagerFactoryBean;
        	
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	

}
