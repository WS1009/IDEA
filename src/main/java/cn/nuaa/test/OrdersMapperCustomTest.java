package cn.nuaa.test;


import java.io.InputStream;
import java.util.List;

import cn.nuaa.po.Orders;
import cn.nuaa.po.OrdersCustom;
import cn.nuaa.po.User;
import cn.nuaa.testmapper.OrdersMapperCustom;
import cn.nuaa.testmapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author WS
 * @date 创建时间：2017-6-23 下午4:10:42
 * @version 1.0
 */
public class OrdersMapperCustomTest {
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
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
		for (Orders orders : list) {
			System.out.println(orders);
		}
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
		sqlSession.close();
	}

	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();
		for (Orders orders : list) {
			//getUser()会进行延迟加载查询用户信息，实现按需加载
			System.out.println(orders.getUser());
		}
		sqlSession.close();
	}
	
	//一级缓存测试
	@Test
	public void testCache1() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//下面的查询使用一个sqlSession
		
		//第一发起请求查询id为1的用户
		User user = userMapper.findUserById(1);
		System.out.println(user);
		
		//创建更新对象
		user.setUsername("李白");
		userMapper.updateUser(user);
		//执行comm操作，清空缓存
		sqlSession.commit();
		//第二次发起请求查询id为2 的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		sqlSession.close();
	}
	
	//二级缓存测试
	@Test
	public void testCache2() throws Exception{
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		// 创建usermapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		//下面的查询使用一个sqlSession
		
		//第一发起请求查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		//这里执行关闭操作，将sqlSession中的数据写到二级缓存区域
		sqlSession1.close();
		
		
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
		
		User user3 = userMapper3.findUserById(1);
		System.out.println(user3);
		sqlSession3.close();
	}
}
