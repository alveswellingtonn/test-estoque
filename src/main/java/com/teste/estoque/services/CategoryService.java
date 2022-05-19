package com.teste.estoque.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.estoque.dtos.CategoryDto;
import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

    @Transactional
	public CategoryDto save(CategoryDto categoryDto) {
    	CategoryEntity entity = new CategoryEntity();
    	entity.setName(categoryDto.getName());
    	entity = categoryRepository.save(entity);
		return new CategoryDto(entity);
	}

	public Page<CategoryEntity> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Transactional
	public void delete(CategoryEntity categoryEntity) {
		categoryRepository.delete(categoryEntity);
	}
}
