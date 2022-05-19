package com.teste.estoque.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.estoque.dtos.CategoryDto;
import com.teste.estoque.dtos.ProductDto;
import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.entities.ProductEntity;
import com.teste.estoque.repositories.CategoryRepository;
import com.teste.estoque.repositories.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

    @Transactional
	public CategoryDto save(CategoryDto categoryDto) {
    	CategoryEntity entity = new CategoryEntity();
    	copyDtoToEntity(categoryDto, entity);
    	entity = categoryRepository.save(entity);
    	//entity.setName(categoryDto.getName());
    	//entity = categoryRepository.save(entity);
		//return new CategoryDto(entity);
    	return new CategoryDto(entity, entity.getProducts());
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
	
	@Transactional
	public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
		CategoryEntity entity = categoryRepository.getById(id);
		copyDtoToEntity(categoryDto, entity);
		return new CategoryDto(entity, entity.getProducts());
	}
	
	private void copyDtoToEntity(CategoryDto dto, CategoryEntity entity) {

		entity.setName(dto.getName());

		entity.getProducts().clear();

		for (ProductDto catDto : dto.getProducts()) {
			Optional<ProductEntity> product = productRepository.findById(catDto.getId());
			entity.getProducts().add(product.get());
		}

	}
}
