package com.zhu.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhu.domain.Order;
import com.zhu.domain.User;
import com.zhu.service.OrderService;
import com.zhu.service.UserService;

/**
 * 订单管理
 */
@Controller
@RequestMapping("/manager")
public class OrderController {

	private final int pageSize=15;

	@Autowired
	private OrderService service;

	@Autowired
	private UserService userService;

	/**
	 * 根据订单状态获取订单
	 * @param state
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllOrderByState")
	public String getAllOrderByState(@RequestParam boolean state,@RequestParam(defaultValue="1")int pageNum,Model model){
		PageInfo<Order> pageInfo=service.getAllOrderByState(state, pageNum,pageSize);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("url","/getAllOrderByState");
		return "manager/listOrder";
	}

	/**
	 * 查询订单详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findOrderById")
	public String findOrderById(@RequestParam String id,Model model) {
		model.addAttribute("order",service.findOrderById(id));
		System.out.println(service.findOrderById(id).getOrdertime());
		return "manager/orderDetail";
	}

	/**
	 * 更新订单状态
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateOrderState")
	public String updateOrderState(@RequestParam String id,Model model) {
		service.updateOrderState(id);
		model.addAttribute("msg","订单发货成功");
		return "manager/right";
	}

}
