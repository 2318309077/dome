package com.ceway.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ceway.demo.model.Order;
import com.ceway.demo.service.OrderService;

@RestController
@RequestMapping("/test")
public class TestNativeCode {
    
	// 声明本地方法 
	//	  public native void sayHello(); 
	//	  public static void main(String[] args) { 
	//   // 加载动态链接库 
	//   System.loadLibrary("nativeCode"); 
	//   TestNativeCode nativeCode = new TestNativeCode(); 
	//   nativeCode.sayHello(); 
	//  } 
    
	@Autowired
    private OrderService orderService;
	
    @PostMapping("/order")
    public String findOrder(){
    	List<Order> order =new ArrayList<>();
    	order = orderService.findOrder();
    	return JSONObject.toJSONString(order);
    }

}
