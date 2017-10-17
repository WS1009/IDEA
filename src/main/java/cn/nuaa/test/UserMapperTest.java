package cn.nuaa.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import cn.nuaa.po.User;
import cn.nuaa.po.UserCustom;
import cn.nuaa.po.UserQueryVo;
import cn.nuaa.testmapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


/** 
 *
 * @author  WS
 * @date 创建时间：2017-6-21 下午9:38:35 
 * @version 1.0 
 */
public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory
		// 配置文件
		String resource = "mybatis/SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用usermapper的方法
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
	//根据用户ID查询用户信息，使用resultmap输出
	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用usermapper的方法
		User user = userMapper.findUserByIdResultMap(3);
		System.out.println(user);
	}

	@Test
	public void testFindUserByUsername() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用usermapper的方法
		List<User> list = userMapper.findUserByUsername("王");
		sqlSession.close();
		System.out.println(list);
	}
	
	@Test
	public void testInsertUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUsername("孙煌");
		user.setSex("1");
		user.setAddress("南航");
		user.setBirthday(new Date());
		
		//调用usermapper的方法
		userMapper.insertUser(user);
		sqlSession.close();
		System.out.println(user);
	}
	
	//用户信息的综合查询
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//创建包装对象
		UserQueryVo userQueryVo=new UserQueryVo();
		
		UserCustom userCustom=new UserCustom();
		//由于使用了动态sql，如果不设置某个值，条件不会拼接在SQL中
		userCustom.setSex("1");
		userCustom.setUsername("王");
		
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(7);
		ids.add(10);
		userQueryVo.setUserCustom(userCustom);
		userQueryVo.setIds(ids);
		List<UserCustom>list=userMapper.findUserList(userQueryVo);
		System.out.println(list);
		
	}

	//通过hashmap查询用户
	@Test
	public void testFindUserByHashmap()throws Exception{
		//获取session
		SqlSession session = sqlSessionFactory.openSession();
		//获限mapper接口实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构造查询条件Hashmap对象
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("username", "李");

		//传递Hashmap对象查询用户列表
		List<User> list = userMapper.findUserByHashmap(map);
		for (User user : list) {
			System.out.println(user);
		}
		//关闭session
		session.close();
	}



	//用户信息的综合查询总数
	@Test
	public void testFindUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//创建包装对象
		UserQueryVo userQueryVo=new UserQueryVo();
		
		UserCustom userCustom=new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("王");
		
		userQueryVo.setUserCustom(userCustom);
		
		int count = userMapper.findUserCount(userQueryVo);
		System.out.println(count);
		
	}
	
	
}
