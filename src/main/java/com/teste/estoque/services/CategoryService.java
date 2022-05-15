package com.teste.estoque.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.repositories.CategoryRepository;

@Service
public class CategoryService {

	/*@Autowired
	CategoryRepository categoryRepository;*/
	
	final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
	public CategoryEntity save(CategoryEntity categoryEntity) {
		return categoryRepository.save(categoryEntity);
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
