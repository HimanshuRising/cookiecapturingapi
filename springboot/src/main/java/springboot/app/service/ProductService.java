package springboot.app.service;

import java.util.List;

import springboot.app.domains.Product;
import springboot.app.exceptions.ProductNotFound;

public interface ProductService {

	public Product create(Product product);
	public Product delete(Long id) throws ProductNotFound;
	public List<Product> findAll();
	public Product update(Product shop) throws ProductNotFound;
	public Product findById(Long id);

}
