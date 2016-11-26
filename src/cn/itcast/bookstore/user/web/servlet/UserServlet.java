package cn.itcast.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * User表述层
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	
	/**
	 * 
	* @Title: alterPassword 
	* @Description: 修改密码 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String alterPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPW = request.getParameter("oldpassword").trim();
		//得到新密码
		String newPW1 = request.getParameter("password1").trim();
		String newPW2 = request.getParameter("password2").trim();
		User user = (User)request.getSession().getAttribute("session_user");
		if(!newPW1.equals(newPW2)){
			request.setAttribute("newword", "两次密码不对");
		}else{
			if(!userService.getPassword(user.getUid(), oldPW)){
				request.setAttribute("newword", "原密码不对");
			} else {
				userService.alterPassword(user.getUid() , newPW1);
				request.setAttribute("newword", "更改了新的密码");
			}
		}
		return "f:/jsps/user/alterpassword.jsp";
	}

	/**
	 * 
	* @Title: search 
	* @Description: 查找用户，用过索引,先判断索引类型 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index = request.getParameter("index");
		User userList = null;
		index = index.trim();
		// 字符  数字  错误 三种
		System.out.println(index);
 		if(index.matches("\\d")){
			//数字类 序号
 			userList = userService.findByID(index);
		} else {
			if(index.matches("\\w")){
				//字符类 用户名
				userList = userService.findByName(index);
			} else {
				//处理错误
	 			request.setAttribute("msg", "输入了什么玩意啊:"+index);
			}
		}
 		if(userList != null ){ 			
 			request.setAttribute("userList", userList);
 		} else {
 			request.setAttribute("msg", "该数据查找失败: "+index);
 		}
 		return "f:/jsps/user/searchuser.jsp";
	}

	/**
	 * 
	* @Title: alterUser 
	* @Description: 修改用户 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void alterUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//	System.out.println("alter method");
		String uid = (String)request.getParameter("uid");
		String pri = (String)request.getParameter("pri");
		System.out.println(pri+"---"+uid);
		User form = new User();
	//	System.out.println(form.toString());
		form.setUid(uid);
		form.setPrivilege(pri);
		userService.alterPrivilege(form);
		request.setAttribute("userList", userService.findAll());
		request.getRequestDispatcher("/jsps/user/alteruser.jsp").forward(request, response);
//		return "f:/jsps/user/alteruser.jsp";
	}

	/**
	 * 
	* @Title: userAll 
	* @Description: 查询到所有用户 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String userAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("userList", userService.findAll());
		return "f:/jsps/user/alteruser.jsp";
	}

	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String who =(String) request.getAttribute("who");
		//System.out.println(who);
		request.setAttribute("userList", userService.findAll());
		return "f:/jsps/user/showuser.jsp";
	}
	
	/**
	 * 退出功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到form中
		 * 2. 输入校验（不写了）
		 * 3. 调用service完成激活
		 *   > 保存错误信息、form到request，转发到login.jsp
		 * 4. 保存用户信息到session中，然后重定向到index.jsp
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			/*
			 * 给用户添加一个购物车，即向session中保存一Cart对象
			 */
			request.getSession().setAttribute("cart", new Cart());
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
	}
	
	/**
	 * 激活功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取参数激活码
		 * 2. 调用service方法完成激活
		 *   > 保存异常信息到request域，转发到msg.jsp
		 * 3. 保存成功信息到request域，转发到msg.jsp
		 */
		String code = request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜，您激活成功了！请马上登录！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 注册功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到form对象中
		 * 2. 补全：uid、code
		 * 3. 输入校验
		 *   > 保存错误信息、form到request域，转发到regist.jsp
		 * 4. 调用service方法完成注册
		 *   > 保存错误信息、form到request域，转发到regist.jsp
		 * 5. 发邮件
		 * 6. 保存成功信息转发到msg.jsp
		 */
		// 封装表单数据
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		// 补全
		form.setUid(CommonUtils.uuid());
		form.setPrivilege("normal");
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
		/*
		 * 输入校验
		 * 1. 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		 */
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 2. 获取form中的username、password、email进行校验
		 */
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3~10之间！");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if(!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email格式错误！");
		}
		/*
		 * 3. 判断是否存在错误信息
		 */
		if(errors.size() > 0) {
			// 1. 保存错误信息
			// 2. 保存表单数据
			// 3. 转发到regist.jsp
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		/*
		 * 调用service的regist()方法
		 */
		try {
			userService.regist(form);
		} catch (UserException e) {
			/*
			 * 1. 保存异常信息
			 * 2. 保存form
			 * 3. 转发到regist.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		/*
		 * 发邮件
		 * 准备配置文件！
		 */
		// 获取配置文件内容
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//获取服务器主机
		String uname = props.getProperty("uname");//获取用户名
		String pwd = props.getProperty("pwd");//获取密码
		String from = props.getProperty("from");//获取发件人
		String to = form.getEmail();//获取收件人
		String subject = props.getProperty("subject");//获取主题
		String content = props.getProperty("content");//获取邮件内容
		content = MessageFormat.format(content, form.getCode());//替换{0}
		
		Session session = MailUtils.createSession(host, uname, pwd);//得到session
		Mail mail = new Mail(from, to, subject, content);//创建邮件对象
		try {
			MailUtils.send(session, mail);//发邮件！
		} catch (MessagingException e) {
		}
		
		
		/*
		 * 1. 保存成功信息
		 * 2. 转发到msg.jsp
		 */
		request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活");
		return "f:/jsps/msg.jsp";
	}
}
