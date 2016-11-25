package cn.itcast.bookstore.user.web.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class ManagerUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
		System.out.println("find All");
		return null;
	}
	

	
}
