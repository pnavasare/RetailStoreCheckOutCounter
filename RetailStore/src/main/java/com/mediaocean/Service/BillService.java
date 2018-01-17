package com.mediaocean.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaocean.Model.Bill;
import com.mediaocean.Model.Product;
import com.mediaocean.doa.BillRep;
import com.mediaocean.doa.ProductRep;
import com.mediaocean.util.ProductCategory;

@Service
public class BillService {
	
	@Autowired
	private BillRep billRep; 
	
	@Autowired
	private ProductRep prodRep;
	
	private static final AtomicLong counter = new AtomicLong(0);

	 public static long getNextNumber(){
	        return counter.incrementAndGet();
	    }
	
	public Bill addProduct(Long billId,String barCodeId,int noOfProducts) {			
		if(prodRep.get(barCodeId) == null || billRep.get(billId)==null) 
			 return null;
		else			 
		  return billRep.addProduct(billId, barCodeId, noOfProducts);
	}
	
	public Bill createBill() {			
		return billRep.create(getNextNumber());
	}
	
	public Bill computeTotalAndCost(Long billId) {
        Bill bill = billRep.get(billId);
        if(bill == null)
        	return null;
        double totalCost = 0.0;
        double totalTax = 0.0;
        Map<String,Integer> productList = bill.getProdList();
        if(productList.size() > 0){
		Set<String> products = bill.getProdList().keySet();
        for(String prod: products){
        	Integer prodQuantity = productList.get(prod);
        	Product product = prodRep.get(prod);
        	 if(product != null){
        	  totalCost += (product.getPrice() * prodQuantity);
        	 if(ProductCategory.CatA.equals(product.getProductCategory()))
        	  totalTax += (product.getPrice() * 0.1* prodQuantity);
        	 else if(ProductCategory.CatB.equals(product.getProductCategory()))
        	  totalTax += (product.getPrice() * 0.2* prodQuantity);		
        	}
        }
        }
		bill.setTotalAmount(totalCost);
		bill.setTotalTax(totalTax);
		billRep.add(bill);
		return bill;
	}

    public Bill getBillbyId(Long id){
    	return billRep.get(id);
    	
    }
    
	public Bill deleteProduct(Long billId,String barCodeId,int noOfProducts) {			
		if(prodRep.get(barCodeId) == null || billRep.get(billId)==null) 
			 return null;
		else
		  return billRep.deleteProduct(billId, barCodeId, noOfProducts);
		
	}
	
	public Bill updateProduct(Long billId,String barCodeId,int noOfProducts) {			
		if(prodRep.get(barCodeId) == null || billRep.get(billId)==null) 
			 return null;
		else
		  return billRep.updateProduct(billId, barCodeId, noOfProducts);
		
	}
	
	
	
	

}
