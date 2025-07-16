package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;


public class ProductDto {

    private Long id;
    private String productName;
    private String productDescription;
    private double price;
    private String productCategory;
    private int stock;
    private int quantity; 
    
    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public ProductDto(Product entity) {
        this.id = entity.getId();
        this.productName = entity.getProductName();
        this.productDescription = entity.getProductDescription();
        this.price = entity.getPrice();
        this.productCategory = entity.getProductCategory();
        this.stock = entity.getStock();
        // quantity burada doğrudan entity’den alınmaz, sepet işlemlerinde kullanıcı tarafından belirlenir.
    }
}
