package com.teste.estoque.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.estoque.entities.ProductEntity;
import com.teste.estoque.repositories.ProductRepository;

@Service
public class ProductService {

	/*@Autowired
	ProductRepository productRepository;*/
	
	final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Transactional
	public ProductEntity save(ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}
    
    public Page<ProductEntity> findAll(Pageable pageable) {
    	return productRepository.findAll(pageable);
    }
    
    public Optional<ProductEntity> findById(Long id) {
    	return productRepository.findById(id);
    }
    
    @Transactional
	public void delete(ProductEntity productEntity) {
		productRepository.delete(productEntity);
	}
}
