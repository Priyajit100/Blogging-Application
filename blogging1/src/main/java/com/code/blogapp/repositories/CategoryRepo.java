package com.code.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<com.code.blogapp.entities.Category, Integer> {

}