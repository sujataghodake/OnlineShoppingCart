package com.fecund.shcart.entity;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
    Category cat;
    Subcategory subcat;
	private int productId;
	private String productName;
	private float totalprice;
private String index;
	private float productPrice;
	private int productquantity;
	private String description;
	public float getTotalprice() {
		return totalprice;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	private double UnitCost;
	private double TotalCost;
	private InputStream image;
	private int quantity;
	private String categoryName;
	private String subcategoryName;
	public double getUnitCost() {
		return UnitCost;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	public double getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setUnitCost(double unitCost) {
		UnitCost = unitCost;
	}
	public Product() {
		super();
	}
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream inputStream) {
		this.image = inputStream;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public Subcategory getSubcat() {
		return subcat;
	}
	public void setSubcat(Subcategory subcat) {
		this.subcat = subcat;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductquantity() {
		return productquantity;
	}
	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}