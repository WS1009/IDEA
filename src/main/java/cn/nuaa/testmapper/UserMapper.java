package cn.nuaa.testmapper;


import cn.nuaa.po.User;
import cn.nuaa.po.UserCustom;
import cn.nuaa.po.UserQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author WS
 * @date 创建时间：2017-6-21 下午8:35:20
 * @version 1.0
 */
public interface UserMapper {
	// 根据ID查询用户的信息
	public User findUserById(int id) throws Exception;
	
	// 根据ID查询用户的信息
	public User findUserByIdResultMap(int id) throws Exception;

	// 添加用户的信息
	public void insertUser(User user) throws Exception;

	// 删除用户的信息
	public void deleteUserById(int id) throws Exception;
	
	//根据用户名称查询用户列表
	public List<User> findUserByUsername(String name) throws Exception;
	
	//综合查询
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

	//通过hashmap查询用户
	public List<User> findUserByHashmap(Map<String,Object> map)throws Exception;

	//综合查询用户总数
	public int findUserCount(UserQueryVo userQueryVo) throws Exception;
	
	public int updateUser(User user) throws Exception;
}
