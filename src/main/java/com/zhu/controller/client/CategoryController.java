package com.zhu.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.domain.Category;
import com.zhu.service.CategoryService;

/**
 * 图书分类
 */
@RestController("ClientCategoryController")
@RequestMapping("/client")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping("/getAllCategory")
	public List<Category> getAllCategory(){
		return service.getAllCategory();
	}
	
	
	@RequestMapping("/findCategoryById")
	public Category findCategoryById(@RequestParam String id){
		return service.findCategoryById(id);
	}

}
