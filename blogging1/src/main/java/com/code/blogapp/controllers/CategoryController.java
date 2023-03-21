package com.code.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.code.blogapp.payloads.ApiResponse;
import com.code.blogapp.payloads.CategoryDto;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.services.CategoryService;
import com.code.blogapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto  createcategoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createcategoryDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer uid){
		CategoryDto updatecategory=this.categoryService.updateCategory(categoryDto,uid);
		return new ResponseEntity<>(updatecategory,HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse>delete(@PathVariable("categoryId") Integer uid){
		this.categoryService.deleteCategory(uid);
		return new ResponseEntity(new ApiResponse("Category deleted",true ),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>get(){
		return new ResponseEntity(this.categoryService.getCategories(),HttpStatus.OK);	
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<UserDto>getAll(@PathVariable Integer categoryId){
		return new ResponseEntity(this.categoryService.getCategory(categoryId),HttpStatus.OK);	
	}
}
