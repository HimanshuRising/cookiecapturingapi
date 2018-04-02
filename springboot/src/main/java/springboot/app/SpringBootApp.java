package springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springboot.app.configuration.AppConfiguration;
import springboot.app.domains.Product;
import springboot.app.service.ProductService;
import springboot.app.service.impl.ProductServiceImpl;

@SpringBootApplication
@Configuration
@Import(AppConfiguration.class)
public class SpringBootApp {

	
	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootApp.class, args);
		ProductService pService = ctx.getBean("productService",ProductService.class);
		
		Product product = new Product();
		product.setName("Laptop");
		product.setPrice(55000.00);
		
		
		System.out.println(pService.create(product));
		
	}
	
}
