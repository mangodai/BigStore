package cn.itcast.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

/**
 * 订单输出
* @ClassName: OrderListServlet 
* @Description: TODO
* @author MangoDai 96555734@qq.com
* @date 2016年11月26日 下午2:29:13 
*
 */
public class OrderListServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	
	public String findByState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int state = Integer.parseInt(request.getParameter("state"));
		List<Order> orderList = orderService.getAll(state);
		request.setAttribute("orderList", orderList );
		return  "f:/adminjsps/admin/order/list.jsp";
	}
	
	/**
	 * @return 
	 * 得到订单
	* @Title: GetAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String GetAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("orderList", orderService.getAll());
		return "f:/adminjsps/admin/order/list.jsp";
	}
}
