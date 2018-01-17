package com.mediaocean.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaocean.Model.Product;
import com.mediaocean.doa.ProductRep;
import com.mediaocean.handler.InValidInputException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRep rep; 
	public Product addProduct(Product product) {
			
		if(rep.get(product.getBarCodeId()) == null) 
		 return rep.add(product,product.getBarCodeId());
		else
		 throw new InValidInputException("Product with barCodeId : " + product.getBarCodeId() + " already exists");

	}
	
	public Product deleteProduct(String barCodeId) {	
     return rep.delete(barCodeId);
	}
	
	public Product updateProduct(Product product,String barCodeId) {
		
		return rep.update(product,barCodeId);
	}
	
	public Product getProduct(String id) {	
     return rep.get(id);
	}
	



}
