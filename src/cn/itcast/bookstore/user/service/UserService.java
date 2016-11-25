package cn.itcast.bookstore.user.service;

import java.util.List;

import org.junit.Test;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

/**
 * 
* @ClassName: UserService 
* @Description: 持久层
* @author MangoDai 96555734@qq.com
* @date 2016年11月23日 上午8:37:28 
*
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	public boolean getPassword(String uid , String pw){
		System.out.println(uid+"--compared--"+pw);
		return userDao.Comparedpassword(uid, pw);
	}
	
	/**
	 * 
	* @Title: alterPassword 
	* @Description: 修改密码
	* @param @param uid
	* @param @param pw    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void alterPassword(String uid , String pw){
		userDao.updatepassword(uid, pw);
	}
	
	/**
	 * 
	* @Title: findByName 
	* @Description: 通过用户名查找id 
	* @param @param index
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	public User findByName(String index){
		User temp = null;
		try {
			temp = userDao.findByUsername(index);			
		} catch (Exception e) {
		}
		return temp;
	}
	
	
	/**
	 * 
	* @Title: findByID 
	* @Description: 查找用户  通过id
	* @param @param uid
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	@Test
	public User findByID(String uid){
		User temp = null;
		try {
			temp = userDao.findByID(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(temp.toString());
		return temp;
	}
	
	/**
	 * 
	* @Title: alterPrivilege 
	* @Description: 修改用户权限
	* @param @param form    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void alterPrivilege(User form){
	//	System.out.println(tmp.toString());
		userDao.updatePrivilege(form.getUid(), form.getPrivilege());
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 返回所有用户 
	* @param @return    设定文件 
	* @return List<User>    返回类型 
	* @throws
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}
	/**
	 * 注册功能
	 * @param form
	 */
	public void regist(User form) throws UserException{
		
		// 校验用户名
		User user= null;
		try {
			user = userDao.findByUsername(form.getUsername());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null) throw new UserException("用户名已被注册！");
		
		// 校验email
		user = userDao.findByEmail(form.getEmail());
		if(user != null) throw new UserException("Email已被注册！");
		
		// 插入用户到数据库
		userDao.add(form);
	}
	
	/**
	 * 激活功能
	 * @throws UserException 
	 */
	public void active(String code) throws UserException {
		/*
		 * 1. 使用code查询数据库，得到user
		 */
		User user = userDao.findByCode(code);
		/*
		 * 2. 如果user不存在，说明激活码错误
		 */
		if(user == null) throw new UserException("激活码无效！");
		/*
		 * 3. 校验用户的状态是否为未激活状态，如果已激活，说明是二次激活，抛出异常
		 */
		if(user.isState()) throw new UserException("您已经激活过了，不要再激活了，除非你想死！");
		
		/*
		 * 4. 修改用户的状态
		 */
		userDao.updateState(user.getUid(), true);
	}
	
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws UserException {
		/*
		 * 1. 使用username查询，得到User
		 * 2. 如果user为null，抛出异常（用户名不存在）
		 * 3. 比较form和user的密码，若不同，抛出异常（密码错误）
		 * 4. 查看用户的状态，若为false，抛出异常（尚未激活）
		 * 5. 返回user
		 */
		User user = null;
		try {
			user = userDao.findByUsername(form.getUsername());
			if(user == null) throw new UserException("用户名不存在！");
			if(!user.getPassword().equals(form.getPassword()))
				throw new UserException("密码错误！");
			if(!user.isState()) throw new UserException("尚未激活！");
			
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
}
