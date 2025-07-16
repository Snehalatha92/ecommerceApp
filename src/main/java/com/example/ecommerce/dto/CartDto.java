package com.example.ecommerce.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.entity.Cart;


public class CartDto {

    private Long id;
    private Double totalPrice;
    private List<ProductDtoWithQuantity> products;
    
    
    

    public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Double getTotalPrice() {
		return totalPrice;
	}




	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}




	public List<ProductDtoWithQuantity> getProducts() {
		return products;
	}




	public void setProducts(List<ProductDtoWithQuantity> products) {
		this.products = products;
	}




	public CartDto(Cart entity) {
        this.id = entity.getId();
        this.products = entity.getProducts().entrySet().stream()
                .map(entry -> new ProductDtoWithQuantity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.totalPrice = this.products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}
