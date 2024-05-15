package com.zhu.controller.client;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.domain.Book;
import com.zhu.domain.Cart;
import com.zhu.domain.CartItem;
import com.zhu.domain.Msg;
import com.zhu.domain.User;
import com.zhu.service.BookService;

/**
 * 用户购物车
 * 用session保存信息
 * @param
 */
@Controller
@RequestMapping("/client")
public class CartController {
    
	@Autowired
	private BookService bookService;

	/**
	 * 添加商品到购物车
	 * @param request
	 * @param id
	 * @param count
	 * @param model
	 * @return
	 */
	@RequestMapping("/addToCart")
	@ResponseBody
	public Msg addToCart(HttpServletRequest request,@RequestParam String id,@RequestParam  int count,Model model) {
		User user=(User) request.getSession().getAttribute("user");    
		Cart cart=(Cart) request.getSession().getAttribute("cart");

		if(user!=null) {
			if(cart==null) {
          	  cart=new Cart();
            }
			if(count < 0||count == 0){
				return new Msg(0,"添加商品数量必须大于0");
			}
			cart.add(bookService.findBookById(id),count);
            request.getSession().setAttribute("cart", cart);
            return new Msg(1,"添加成功");
		}else { 
			return new Msg(0,"对不起，请先登录");  
		}
	}

	/**
	 * 修改购物车内商品数量
	 * @param session
	 * @param id
	 * @param count
	 */
	@RequestMapping("/changeCount")
	@ResponseBody
	public void changeCount(HttpSession session,@RequestParam String id,@RequestParam int count) {
		Cart cart=(Cart) session.getAttribute("cart");
		cart.changeCount(id,count);
		session.setAttribute("cart", cart);
	}

	/**
	 * 从购物车内删除商品
	 * @param session
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteFromCart")
	public String deleteFromCart(HttpSession session,@RequestParam String id,Model model) {

		Cart cart=(Cart) session.getAttribute("cart");
	    cart.remove(id); 
	    //这里是因为jsp不可以使用el表达式获得size，所以只能这样写
	    if(cart.getCartItems().size()==0) {
	    	session.removeAttribute("cart");
	    }else {
	    	session.setAttribute("cart",cart);
	    }
	      return "client/listCart";
	}

	/**
	 * 批量删除商品
	 * @param session
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteManyFromCart")
	@ResponseBody
	public Msg deleteManyFromCart(HttpSession session,String ids,Model model) {
		Cart cart=(Cart) session.getAttribute("cart");
		String[] idsArr=ids.split("-");
		for(String s :idsArr) {
			cart.remove(s);
		}
		if(cart.getCartItems().size()==0) {
	    	session.removeAttribute("cart");
	    }else {
	    	session.setAttribute("cart",cart);
	    }
	      return new Msg(1,"删除成功");
	}

	/**
	 * 清空购物车
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session,Model model){
	    session.removeAttribute("cart");
	    return "client/listCart";
	}	
}
