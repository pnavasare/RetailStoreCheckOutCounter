package com.mediaocean.doa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mediaocean.Model.Product;
import com.mediaocean.handler.DataNotFoundException;

@Repository
public class ProductRep {
  Map<String,Product> productsMap = new HashMap<String,Product>();
  
  public Product add(Product product,String barCodeId){
	  return productsMap.put(barCodeId,product);	  
  }
  
  public Product get(String barCodeId){
	  return productsMap.get(barCodeId);	  
  }
  
  public Product delete(String barCodeId){
	  return productsMap.remove(barCodeId);	  
  }
  
  public Product update(Product product,String barCodeId){
	  if(productsMap.get(barCodeId)==null)
		return null;  
	  return productsMap.put(barCodeId,product);	  
  }
}
