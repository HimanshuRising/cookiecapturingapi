package springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.app.domains.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
