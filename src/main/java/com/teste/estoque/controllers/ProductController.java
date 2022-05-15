package com.teste.estoque.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.estoque.dtos.ProductDto;
import com.teste.estoque.entities.ProductEntity;
import com.teste.estoque.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	/*@Autowired
	private ProductService productService;*/
	
	final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveProducty(@RequestBody ProductDto productDto){
        
        var productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        //categoryEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productEntity));
    }

}
