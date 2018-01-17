package com.mediaocean.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mediaocean.Model.Product;
import com.mediaocean.Service.ProductService;
import com.mediaocean.handler.DataNotFoundException;
import com.mediaocean.util.ProductCategory;

@RestController
@EnableWebMvc
public class ProductController {
	//final Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/products/{barCodeId}", method = RequestMethod.PUT,produces = "application/json")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable String barCodeId) {
		Product prod = productService.updateProduct(product,barCodeId);
		if(prod == null )
			throw new DataNotFoundException("Product with id : " + barCodeId + " does not Exist. ");
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{barCodeId}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Product> getProduct(@PathVariable String barCodeId) {
		Product prod = productService.getProduct(barCodeId);
		if(prod ==null )
			throw new DataNotFoundException("Product with id : " + barCodeId + " does not Exist. ");
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST,consumes="application/json",produces = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product prod = productService.addProduct(product);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI prodUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{barCodeId}").buildAndExpand(product.getId())
				.toUri();		
		responseHeaders.setLocation(prodUri);
		return new ResponseEntity<Product>(product, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{barCodeId}", method = RequestMethod.DELETE,produces = "application/json")
	public ResponseEntity<String> deleteProduct(@PathVariable String barCodeId) {
		Product prod = productService.deleteProduct(barCodeId);
		if(prod == null )
			throw new DataNotFoundException("Product with id : " + barCodeId + " does not Exist. ");
		return new ResponseEntity<String>("{\"status\": \"success\"}", HttpStatus.OK);
	}
	
	
}
