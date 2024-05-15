package com.zhu.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhu.domain.Category;
import com.zhu.domain.Msg;
import com.zhu.service.CategoryService;

/**
 * 分类修改
 */
@Controller
@RequestMapping("/manager")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	/**
	 * 获取所有分类
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllCategory")
	public String getAllCategory(Model model){
		model.addAttribute("categories",service.getAllCategory());
		return "manager/listCategory";
	}

	/**
	 *	添加图书时获取分类列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/forAddBookJsp")
	public String ForAddBookJsp(Model model) {
		model.addAttribute("categories",service.getAllCategory());
		return "manager/addBook";
	}

	/**
	 * 根据分类id获取分类
	 * @param id
	 * @return
	 */
	@RequestMapping("/findCategoryById")
	@ResponseBody
	public Msg findCategoryById(@RequestParam String id) {
		return new Msg(1,service.findCategoryById(id));
	}

	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	@RequestMapping("/addCategory")
	@ResponseBody
	public Msg addCategory(Category category) {
		service.addCategory(category);
		return new Msg(1,"添加成功");
	}

	/**
	 * 更新分类
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	@ResponseBody
	public Msg updateCategory(Category category) {
		try {
			service.updateCategory(category);
		}catch(Exception e){
			e.printStackTrace();
			return new Msg(0,"更新失败");
		}
		return new Msg(1,"更新成功");
	}

	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCategory")
	@ResponseBody
	public Msg deleteCategory(@RequestParam String id) {
		boolean flag=service.deleteCategory(id);
		if(flag) {
			return new Msg(1,"删除成功");
		}else { 
			return new Msg(0,"该类别下存有书籍，不可删除");
		}
	}

}
