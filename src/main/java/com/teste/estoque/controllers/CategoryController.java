package com.teste.estoque.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.teste.estoque.dtos.CategoryDto;
import com.teste.estoque.entities.CategoryEntity;
import com.teste.estoque.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){
    	categoryDto = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }
    
    @GetMapping
    public ResponseEntity<Page<CategoryEntity>> getAllCategories(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
    	return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCategory(@PathVariable(value = "id") Long id){
        Optional<CategoryEntity> categoryEntityOptional = categoryService.findById(id);
        if (!categoryEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryEntityOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id){
        Optional<CategoryEntity> categoryEntityOptional = categoryService.findById(id);
        if (!categoryEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        categoryService.delete(categoryEntityOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid CategoryDto categoryDto){
        Optional<CategoryEntity> categoryEntityOptional = categoryService.findById(id);
        if (!categoryEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        var categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto, categoryEntity);
        categoryEntity.setId(categoryEntityOptional.get().getId());
        //categoryEntity.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
