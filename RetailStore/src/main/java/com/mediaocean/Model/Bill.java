package com.mediaocean.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BILLS")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double totalAmount = 0.0;

	private double totalTax = 0.0;

    public Bill(long id) {
		super();
		this.id = id;
	}

	Map<String,Integer> prodList= new HashMap<String,Integer>();



	public Map<String, Integer> getProdList() {
		return prodList;
	}

	public void setProdList(Map<String, Integer> prodList) {
		this.prodList = prodList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

}
