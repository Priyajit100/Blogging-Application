package com.code.blogapp.services.impl;



import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.Post;
import com.code.blogapp.entities.User;
import com.code.blogapp.exceptions.ResourceNotFoundException;
import com.code.blogapp.payloads.PostDto;
import com.code.blogapp.payloads.PostResponse;
import com.code.blogapp.repositories.CategoryRepo;
import com.code.blogapp.repositories.PostRepo;
import com.code.blogapp.repositories.UserRepo;
import com.code.blogapp.services.PostService;



@Service
public class PostServiceImpl implements PostService {
	  @Autowired
	    private PostRepo postRepo;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		 User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));
com.code.blogapp.entities.Category   category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));
Post post = this.modelMapper.map(postDto, Post.class);
post.setImageName("default.png");
post.setAddedDate(new Date());;
post.setUser(user);
post.setCategory(category);

Post newPost = this.postRepo.save(post);

return this.modelMapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		   Post post = this.postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

	        

	        post.setTitle(postDto.getTitle());
	        post.setContent(postDto.getContent());
	        post.setImageName(postDto.getImageName());
	       


	        Post updatedPost = this.postRepo.save(post);
	        return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		  Post post = this.postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

	        this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		  Post post = this.postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
	        return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		 com.code.blogapp.entities.Category cat = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
	        List<Post> posts = this.postRepo.findByCategory(cat);

	        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
	                .collect(Collectors.toList());

	        return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		   User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
	        List<Post> posts = this.postRepo.findByUser(user);

	        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
	                .collect(Collectors.toList());

	        return postDtos;
	}


	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		   Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

	        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

	        Page<Post> pagePost = this.postRepo.findAll(p);

	        List<Post> allPosts = pagePost.getContent();

	        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
	                .collect(Collectors.toList());

	        PostResponse postResponse = new PostResponse();

	        postResponse.setContent(postDtos);
	        postResponse.setPageNumber(pagePost.getNumber());
	        postResponse.setPageSize(pagePost.getSize());
	        postResponse.setTotalElements(pagePost.getTotalElements());

	        postResponse.setTotalPages(pagePost.getTotalPages());
	        postResponse.setLastPage(pagePost.isLast());

	        return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		 List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
	        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	        return postDtos;
	}

}