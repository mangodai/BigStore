package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserNotFoundException;
import cn.itcast.jdbc.TxQueryRunner;


public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void updatepassword(String uid , String pw){
		try {
			String sql =  "update tb_user set password=? where uid=?";
			qr.update(sql , pw , uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 查找所有用户 
	* @param @return    设定文件 
	* @return List<User>    返回类型 
	* @throws
	 */
	public List<User> findAll() {
		try {
			String sql = "select * from tb_user;";
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 * @throws UserNotFoundException 
	 */
	public User findByUsername(String username) throws UserNotFoundException {
		try {
			String sql = "select * from tb_user where username=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch(SQLException e) {
			throw new UserNotFoundException(e);
		}
	}
	
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByEmail(String email) {
		try {
			String sql = "select * from tb_user where email=?";
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 插入User
	 * @param user
	 */
	public void add(User user) {
		try {
			String sql = "insert into tb_user values(?,?,?,?,?,?,?);";
			Object[] params = {user.getUid(), user.getUsername(), 
					user.getPassword(), user.getPrivilege(), user.getEmail(), user.getCode(),
					user.isState()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按激活码查询
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		try {
			String sql = "select * from tb_user where code=?";
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public User findByID(String id) throws UserNotFoundException{
		try {
			String sql = "select * from tb_user where uid=?";
			return qr.query(sql, new BeanHandler<User>(User.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改指定用户的指定状态
	 * @param uid
	 * @param state
	 */
	public void updateState(String uid, boolean state) {
		try {
			String sql = "update tb_user set state=? where uid=?";
			qr.update(sql, state, uid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void updatePrivilege(String uid , String privilege){
		try {
			String sql = "update tb_user set privilege=? where uid=?";
			qr.update(sql, privilege, uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean Comparedpassword(String uid, String pw) {
		try {
			String sql = "select * from tb_user where uid=?";
			String password = qr.query(sql, new BeanHandler<User>(User.class), uid).getPassword();
			return password.equals(pw);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
