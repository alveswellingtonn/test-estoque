package com.teste.estoque.dtos;

import java.io.Serializable;

import com.teste.estoque.entities.CategoryEntity;

public class CategoryDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public CategoryDto(){
		
	}

	public CategoryDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CategoryDto(CategoryEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
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

}
