package com.mediaocean.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mediaocean.util.ProductCategory;
@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String barCodeId;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductCategory productCategory;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private double price;

	public Product() {
		super();
	}

	public Product(String barCodeId, double price, String name, ProductCategory productCategory) {
		super();
		this.barCodeId = barCodeId;
		this.price = price;
		this.name = name;
		this.productCategory = productCategory;
	}

	public String getBarCodeId() {
		return barCodeId;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setBarCodeId(String barCodeId) {
		this.barCodeId = barCodeId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}


}
