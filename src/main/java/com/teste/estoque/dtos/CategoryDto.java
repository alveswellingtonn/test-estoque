package com.teste.estoque.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.entities.ProductEntity;

public class CategoryDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private List<ProductDto> products= new ArrayList<>();

	public CategoryDto() {
	}

	public CategoryDto(CategoryEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	public CategoryDto(CategoryEntity entity, List<ProductEntity> list) {
		this(entity);
		list.forEach(cat -> this.products.add(new ProductDto(cat)));
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

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

}
