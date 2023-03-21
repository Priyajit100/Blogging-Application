package com.code.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blogapp.entities.Comment;



public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}
