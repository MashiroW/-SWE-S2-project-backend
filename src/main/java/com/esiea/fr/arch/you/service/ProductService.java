package com.esiea.fr.arch.you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esiea.fr.arch.you.dto.ProductDto;
import com.esiea.fr.arch.you.entity.Product;
import com.esiea.fr.arch.you.mapper.MapperProduct;
import com.esiea.fr.arch.you.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MapperProduct mapperProduct;
	
	
	public void createProduct(ProductDto productDto){
		Product product = mapperProduct.map(productDto);
		productRepository.save(product);
		
		System.out.println("The product "+product.getName() + " (ID = "+product.getId()+") has been added");
	}
		
}
