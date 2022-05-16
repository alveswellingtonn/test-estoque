package com.teste.estoque.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Object> saveProducty(@RequestBody ProductDto productDto) {
        
        var productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        //categoryEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productEntity));
    }

    @GetMapping
    public ResponseEntity<Page<ProductEntity>>getAllProducts(@PageableDefault (page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    	return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id) {
        Optional<ProductEntity> productEntityOptional = productService.findById(id);
        if (!productEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productEntityOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<ProductEntity> productEntityOptional = productService.findById(id);
        if (!productEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(productEntityOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid ProductDto productDto){
        Optional<ProductEntity> productEntityOptional = productService.findById(id);
        if (!productEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setId(productEntityOptional.get().getId());
        //categoryEntity.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productEntity));
    }
}
