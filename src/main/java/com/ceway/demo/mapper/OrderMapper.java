package com.ceway.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ceway.demo.model.Order;

public interface OrderMapper {
	
	@Select("select id,login,name from order1")
	List<Order> findOrder(); 
}
