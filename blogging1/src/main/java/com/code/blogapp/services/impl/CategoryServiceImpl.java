package com.code.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.Category;
import com.code.blogapp.entities.User;
import com.code.blogapp.exceptions.ResourceNotFoundException;
import com.code.blogapp.payloads.CategoryDto;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.repositories.CategoryRepo;
import com.code.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
		@Override
		public CategoryDto createCategory(CategoryDto categoryDto) {
			Category category =this.CategoryDtoToCategory(categoryDto);
			Category saved=this.categoryRepo.save(category);// TODO Auto-generated method stub
			return this.CategoryToDto(category);
			
		}
		@Override
		public CategoryDto updateCategory(CategoryDto categoryDto, Integer Id) {
			Category category =this.categoryRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Category","id",Id));// TODO Auto-generated method stub
			category.setTitle(categoryDto.getTitle());
			category.setDescription(categoryDto.getDescription());
			
			Category updated=this.categoryRepo.save(category);
			return this.CategoryToDto(updated);
		}
		@Override
		public	CategoryDto getCategory(Integer Id) {
			Category category1 =this.categoryRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Category","id",Id));
			
			return this.CategoryToDto(category1);
			
		}
		@Override
		public List<CategoryDto> getCategories(){
			
			List<Category>category=	this.categoryRepo.findAll();
			List<CategoryDto>categoryDtos=category.stream().map(category1 ->this.CategoryToDto(category1)).collect(Collectors.toList());
				// TODO Auto-generated method stub
				return categoryDtos;
		}
		@Override
		public	void deleteCategory(Integer Id) {
			Category category =this.categoryRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Category","id",Id));
			this.categoryRepo.delete(category);
		}
	private CategoryDto CategoryToDto(Category category) {
		// TODO Auto-generated method stub
CategoryDto categorydto=this.modelMapper.map(category, CategoryDto.class);
		
		return categorydto;
	}

	private Category CategoryDtoToCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=this.modelMapper.map(categoryDto, Category.class);
		
		return category;
	}

	

	

}
