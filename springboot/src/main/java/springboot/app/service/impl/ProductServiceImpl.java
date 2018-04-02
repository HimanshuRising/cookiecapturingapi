package springboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.app.domains.Product;
import springboot.app.exceptions.ProductNotFound;
import springboot.app.repository.ProductRepository;
import springboot.app.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	@Transactional
	public Product create(Product product) {
		Product createdProduct = product;
		productRepository.save(product);
		return createdProduct;
	}

	@Override
	@Transactional(rollbackFor=ProductNotFound.class)
	public Product delete(Long id) throws ProductNotFound {
		Optional<Product> deletedProduct = productRepository.findById(id);
		if(deletedProduct.isPresent()){
			productRepository.delete(deletedProduct.get());
			return deletedProduct.get();
		}else{
			throw new ProductNotFound("Product Not Found "+id);
		}
		
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=ProductNotFound.class)
	public Product update(Product product) throws ProductNotFound {
		Product updatedProduct = product;
		Optional<Product> deletedProduct = productRepository.findById(product.getId());
		if(!deletedProduct.isPresent()){
			throw new ProductNotFound("Product Not Found "+product.getId());
		}
		updatedProduct.setName(product.getName());
		updatedProduct.setPrice(product.getPrice());
		return updatedProduct;
	}

	@Override
	@Transactional
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

}
