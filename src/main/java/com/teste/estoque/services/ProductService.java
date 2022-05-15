package com.teste.estoque.services;

import javax.transaction.Transactional;

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
}
