package com.esiea.fr.arch.you.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.esiea.fr.arch.you.dto.ProductDto;
import com.esiea.fr.arch.you.entity.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {
	
	public ProductDto findById(long id);
	
	@Query("SELECT p from Product p Where p.id = : id")
	public ProductDto getProductById(@Param("id") long id);

}
