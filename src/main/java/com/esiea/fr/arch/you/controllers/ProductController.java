package com.esiea.fr.arch.you.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esiea.fr.arch.you.dto.ProductDto;
import com.esiea.fr.arch.you.entity.Product;
import com.esiea.fr.arch.you.exceptions.ResourceNotFoundException;
import com.esiea.fr.arch.you.repository.ProductRepository;
import com.esiea.fr.arch.you.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	
	
    //Update product
    @RequestMapping(value = "/api/v1/products/edit/{id}", method = RequestMethod.POST, consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId, @RequestBody ProductDto productDetails) throws ResourceNotFoundException {
    	Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: "+productId));
    	
    	product.setId(productDetails.getId());
    	product.setName(productDetails.getName());
    	product.setPrice(productDetails.getPrice());
    	
    	Product updatedProduct = productRepository.save(product);
    	return ResponseEntity.ok().body(updatedProduct);
  
    }
    
    //GET OUR PRODUCTS LIST
    @GetMapping("/api/v1/products/list")
    public ResponseEntity<Iterable<Product>> getAllProducts(){
        return  ResponseEntity.ok().body(productRepository.findAll());
    }
    
    //Get products by id
    @GetMapping("/api/v1/products/get/{id}")
    public ResponseEntity<Product> getProdctById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException{
    	Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: "+productId));
    	return ResponseEntity.ok().body(product);
    }    
    
	//Adding a product
    @RequestMapping(value = "/api/v1/products/add", method = RequestMethod.POST, consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) 
    {
    	productService.createProduct(product);
    	return new ResponseEntity<ProductDto>(product, HttpStatus.OK);    
    }
    
    //Delete product
    @DeleteMapping("/api/v1/products/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    	Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: "+id));
    	productRepository.delete(product);
    	System.out.println("The product "+product.getName() + " (ID = "+product.getId()+") has been removed");
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("deleted", Boolean.TRUE);
    	return ResponseEntity.ok(response);
    }
    
}
