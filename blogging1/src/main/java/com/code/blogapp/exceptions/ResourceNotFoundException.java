package com.code.blogapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
String resource;
String field;
long value;
public ResourceNotFoundException(String resource, String field, long value) {
	super(String.format("%s not found with %s : %s",resource,field,value ) );
	this.resource = resource;
	this.field = field;
	this.value = value;
}
}
