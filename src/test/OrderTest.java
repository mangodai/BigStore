package test;

import java.util.List;

import org.junit.Test;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;

public class OrderTest {
	
	@Test
	public void findAll(){
		OrderDao dao = new OrderDao();
		List<Order> list = dao.findAll();
		for(Order i : list){
			System.out.println(i.getOid()+"--"+i.getOrdertime()+":");
			
			for(OrderItem j: i.getOrderItemList() ){
				System.out.println(j.toString());
			}
		}
	}
	
	@Test 
	public void findByState(){
		int state = 2;
		OrderDao dao  = new OrderDao();
		for(Order i : dao.findByState(state)){
			System.out.println(i.getOid()+"--"+i.getOrdertime()+":");
			
			for(OrderItem j: i.getOrderItemList() ){
				System.out.println(j.toString());
			}
		}
	}
}
