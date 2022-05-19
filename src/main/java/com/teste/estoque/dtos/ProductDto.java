package com.teste.estoque.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.entities.ProductEntity;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private int quantity;
	
	private List<CategoryDto> categories= new ArrayList<>();
	
	public ProductDto(){
		
	}

	public ProductDto(Long id, String name, String description, Double price, int quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public ProductDto(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.quantity = entity.getQuantity();
	}
	
	public ProductDto(ProductEntity entity, Set<CategoryEntity> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDto(cat)));
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

}
