package cn.itcast.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;
/**
 * 持久层
* @ClassName: OrderService 
* @Description: TODO
* @author MangoDai 96555734@qq.com
* @date 2016年11月26日 下午2:31:57 
*
 */
public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public List<Order> getAll(int state){
		return orderDao.findByState(state);
	}
	
	/**
	 * 得到所有对象订单
	* @Title: getAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List<Order>    返回类型 
	* @throws
	 */
	public List<Order> getAll(){
		return orderDao.findAll();
	}
	
	/**
	 * 支付方法
	 * @param oid
	 */
	public void zhiFu(String oid) {
		/*
		 * 1. 获取订单的状态
		 *   * 如果状态为1，那么执行下面代码
		 *   * 如果状态不为1，那么本方法什么都不做
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 1) {
			// 修改订单状态为2
			orderDao.updateState(oid, 2);
		}
	}
	
	/**
	 * 添加订单
	 * 需要处理事务
	 * @param order
	 */
	public void add(Order order) {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入订单中的所有条目
			
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch(Exception e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/**
	 * 确认收货
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException {
		/*
		 * 1. 校验订单状态，如果不是3，抛出异常
		 */
		int state = orderDao.getStateByOid(oid);//获取订单状态
		if(state != 3) throw new OrderException("订单确认失败，您不是什么好东西！");
		
		/*
		 * 2. 修改订单状态为4，表示交易成功
		 */
		orderDao.updateState(oid, 4);
	}
	
	/**
	 * 卖家发货
	* @Title: deliver 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oid    设定文件 
	* @return void    返回类型 
	* @throws OrderException 
	 */
	public void deliver(String oid) throws OrderException {
		/*
		 * 1. 校验订单状态，如果不是1，抛出异常
		 * 订单状态有四种：1未付款 2已付款但未发货 3已发货但未确认收货 4已确认交易成功
		 */
		int state = orderDao.getStateByOid(oid);//获取订单状态
		if(state != 2) throw new OrderException("你还敢不付钱，就要我发货");
		
		/*
		 * 2. 修改订单状态为3，表示等待 收获
		 */
		orderDao.updateState(oid, 3);
	}
}
