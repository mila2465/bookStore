package com.zhu.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zhu.domain.Book;
import com.zhu.domain.Category;
import com.zhu.service.BookService;
import com.zhu.service.CategoryService;

/**
 *	用户图书查询
 */
@Controller("ClientBookController")
@RequestMapping("/client")
public class BookController {
	private final int pageSize=6;
	
	@Autowired
	private BookService service;
	@Autowired
    private CategoryService categoryService;

	/**
	 * 查询所有图书
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllBook")
	public String getAllBookAndCategory(@RequestParam(defaultValue="1")int pageNum,Model model){
		PageInfo<Book> pageInfo=service.getAllBook(pageNum,pageSize);
		List<Category> categories=categoryService.getAllCategory();	
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("categories",categories);
		model.addAttribute("url","getAllBook");	
		return "client/body";  
	}

	/**
	 * 分类查询图书
	 * @param category_id
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllBookByCategory")
	public String getAllBookByCategory(@RequestParam String category_id,
			         @RequestParam(defaultValue="1")int pageNum,Model model){
		PageInfo<Book> pageInfo=service.getAllBookByCategory(category_id,pageNum,pageSize);
	    List<Category> categories=categoryService.getAllCategory();
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("categories",categories);
		model.addAttribute("url","getAllBookByCategory");
		return "client/body";
	}

	/**
	 *	按书名模糊查询图书
	 * @param book
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryBook")
	public String queryBook(Book book,@RequestParam(defaultValue="1")int pageNum,Model model) {
		PageInfo<Book> pageInfo=service.queryBook(book, pageNum,pageSize);
	    List<Category> categories=categoryService.getAllCategory();
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("categories",categories);
		model.addAttribute("url","queryBook");
		model.addAttribute("query_name",book.getName());
		return "client/body";
	}

	/**
	 * 查询图书详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findBookById")
	public String findBookById(@RequestParam String id,Model model) {
		Book book=service.findBookById(id);
		model.addAttribute("book",book);
	     return "client/bookDetail"; 
	}
	
}
