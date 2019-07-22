package com.ceway.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceway.demo.config.DS;
import com.ceway.demo.mapper.OrderMapper;
import com.ceway.demo.model.Order;
import com.ceway.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	@DS("readTestDb")
	public List<Order> findOrder() {
		return orderMapper.findOrder();
	}

}
