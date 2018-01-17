package com.mediaocean.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mediaocean.Model.Bill;
import com.mediaocean.Service.BillService;
import com.mediaocean.handler.DataNotFoundException;



@RestController
@EnableWebMvc
public class BillController {

	@Autowired
	private BillService billService;
	
	@RequestMapping(value = "/bill/{billId}/{barCodeId}", method = RequestMethod.POST,consumes="text/plain",produces = "application/json")
	public ResponseEntity<Bill> addProduct(@RequestParam("quan") int quantity,@PathVariable Long billId,@PathVariable String barCodeId) {
		Bill bill = billService.addProduct(billId,barCodeId,quantity);
		if(bill == null )
			throw new DataNotFoundException("Product or Bill is not present !! ");
		HttpHeaders responseHeaders = new HttpHeaders();
		URI prodUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{billId}").buildAndExpand(billId)
				.toUri();		
		responseHeaders.setLocation(prodUri);
		return new ResponseEntity<Bill>(responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/bill/{billId}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Bill> printBill(@PathVariable Long billId) {
		Bill bill = billService.computeTotalAndCost(billId);
		if(bill == null )
			throw new DataNotFoundException("Bill with id : " + billId + " does not Exist. ");
		return new ResponseEntity<Bill>(bill,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/bills", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<Bill> createBill() {
		Bill bill = billService.createBill();
		HttpHeaders responseHeaders = new HttpHeaders();
		URI prodUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{billId}").buildAndExpand(bill.getId())
		.toUri();		
		responseHeaders.setLocation(prodUri);
		return new ResponseEntity<Bill>(bill, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/bill/{billId}/{barCodeId}", method = RequestMethod.DELETE,consumes="text/plain",produces = "application/json")
	public ResponseEntity<Bill> deleteProduct(@RequestParam("quan") int quantity,@PathVariable Long billId,@PathVariable String barCodeId) {
		Bill bill = billService.deleteProduct(billId,barCodeId,quantity);
		if(bill == null )
			throw new DataNotFoundException("Product or Bill is not present !! ");
		HttpHeaders responseHeaders = new HttpHeaders();
		URI prodUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{billId}").buildAndExpand(billId)
				.toUri();		
		responseHeaders.setLocation(prodUri);
		return new ResponseEntity<Bill>(responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bill/{billId}/{barCodeId}", method = RequestMethod.PUT,consumes="text/plain",produces = "application/json")
	public ResponseEntity<Bill> updateProduct(@RequestParam("quan") int quantity,@PathVariable Long billId,@PathVariable String barCodeId) {
		Bill bill = billService.updateProduct(billId,barCodeId,quantity);
		if(bill == null )
			throw new DataNotFoundException("Product or Bill is not present !! ");
		HttpHeaders responseHeaders = new HttpHeaders();
		URI prodUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{billId}").buildAndExpand(billId)
				.toUri();		
		responseHeaders.setLocation(prodUri);
		return new ResponseEntity<Bill>(responseHeaders, HttpStatus.OK);
	}
	
	
}
