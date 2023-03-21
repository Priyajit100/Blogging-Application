package com.code.blogapp.services;
import com.code.blogapp.payloads.CategoryDto;
import java.util.List;



public interface CategoryService {
	// create
		public CategoryDto createCategory(CategoryDto categoryDto);

		// update
public	CategoryDto updateCategory(CategoryDto categoryDto, Integer Id);

		// delete
	public	void deleteCategory(Integer Id);

		// get
	public	CategoryDto getCategory(Integer Id);

		// get All

		public List<CategoryDto> getCategories();
}
