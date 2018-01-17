package com.mediaocean.doa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mediaocean.Model.Bill;
import com.mediaocean.handler.DataNotFoundException;
import com.mediaocean.handler.InValidInputException;


@Repository
public class BillRep {
	private Map<Long,Bill> billsMap = new HashMap<Long,Bill>();
	 
	public Bill addProduct(Long billId,String barCodeId,int noOfProducts){
		  Bill bill = billsMap.get(billId);
		  if(bill == null)
			  return null;
		  if(bill.getProdList().get(barCodeId)!=null)
			  throw new InValidInputException("Product Already Exist in the Bill . Please update !!");
		  bill.getProdList().put(barCodeId,noOfProducts);
		  billsMap.put(billId, bill);
		  return bill;

	  }
	
	public Bill get(Long billId){
		return billsMap.get(billId);
	}
	
	public Bill add(Bill bill){
		return billsMap.put(bill.getId(),bill);
	}
	
	public Bill create(long id){
		Bill bill = new Bill(id);
		billsMap.put(bill.getId(),bill);
		return bill;
	}
	
	public Bill deleteProduct(Long billId,String barCodeId,int noOfProducts){
		  Bill bill = billsMap.get(billId);
		  if(bill == null)
			  return null;
		  if(bill.getProdList().get(barCodeId)==null)
			  throw new InValidInputException("Product does not exist in the Bill . Please add it first !!");
		  int actualQuantity = bill.getProdList().get(barCodeId);
		  if(noOfProducts == actualQuantity)
			  bill.getProdList().remove(barCodeId);
		  else if(noOfProducts > actualQuantity)
			  throw new InValidInputException("Quantity of products entered is greater than actuall !!");
		  else
		     bill.getProdList().put(barCodeId,actualQuantity-noOfProducts);
		  billsMap.put(billId, bill);
		  return bill;

	 }
	
	public Bill updateProduct(Long billId,String barCodeId,int noOfProducts){
		  Bill bill = billsMap.get(billId);
		  if(bill == null )
			  return null;
		  if(bill.getProdList().get(barCodeId)==null)
			  throw new InValidInputException("Product does not exist in the Bill . Please add it first !!");
		  int actualQuantity = bill.getProdList().get(barCodeId);		  
		  bill.getProdList().put(barCodeId,actualQuantity+noOfProducts);
		  billsMap.put(billId, bill);
		  return bill;

	 }
	
	
}
