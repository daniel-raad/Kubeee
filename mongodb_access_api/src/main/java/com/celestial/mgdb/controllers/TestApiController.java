package com.celestial.mgdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celestial.mgdb.mappings.RecipesMapper;


@RestController	
@RequestMapping("/api")
public class TestApiController 
{
	private	RecipesMapper recipesMapper;
	
	@Autowired
	public	TestApiController( RecipesMapper rm )
	{
		recipesMapper = rm;
	}
	
	@GetMapping("/status")
	public	String getStatus()
	{
		return "Healthy";
	}
	
	@GetMapping("/recipes")
	public	String getRecipies()
	{
		recipesMapper.connectToDB();
		
		return recipesMapper.getRecipesAsString().toString();
	}

}
