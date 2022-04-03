package com.esiea.fr.arch.you.mapper;

import org.springframework.stereotype.Component;

import com.esiea.fr.arch.you.dto.ProductDto;
import com.esiea.fr.arch.you.entity.Product;

@Component
public class MapperProduct {
	
	public Product map (ProductDto productDto) {
	
	Product product = new Product();
	
	product.setName(productDto.getName());
	product.setId(productDto.getId());
	product.setPrice(productDto.getPrice());	
	
	return product;
	}

}
