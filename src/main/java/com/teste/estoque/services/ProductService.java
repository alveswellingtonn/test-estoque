package com.teste.estoque.services;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public ProductDto save(ProductDto productDto) {
		ProductEntity entity = new ProductEntity();
		copyDtoToEntity(productDto, entity);
		entity = productRepository.save(entity);
		return new ProductDto(entity, entity.getCategories());
	}

	public Page<ProductEntity> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Optional<ProductEntity> findById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional
	public @Valid ProductDto updateProduct(Long id, @Valid ProductDto productDto) {
		ProductEntity entity = productRepository.getById(id);
		copyDtoToEntity(productDto, entity);
		entity = productRepository.save(entity);
		return new ProductDto(entity, entity.getCategories());
	}

	@Transactional
	public void delete(ProductEntity productEntity) {
		productRepository.delete(productEntity);
	}

	private void copyDtoToEntity(ProductDto dto, ProductEntity entity) {

		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());

		entity.getCategories().clear();

		for (CategoryDto catDto : dto.getCategories()) {
			Optional<CategoryEntity> category = categoryRepository.findById(catDto.getId());
			entity.getCategories().add(category.get());
		}

	}

}
