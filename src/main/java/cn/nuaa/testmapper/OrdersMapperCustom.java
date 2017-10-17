package cn.nuaa.testmapper;


import cn.nuaa.po.Orders;
import cn.nuaa.po.OrdersCustom;
import cn.nuaa.po.User;

import java.util.List;

/**
 * 
 * @author WS
 * @date 创建时间：2017-6-23 下午4:03:11
 * @version 1.0
 */
public interface OrdersMapperCustom {

	//查询订单关联用户查询
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	//查询订单关联用户查询-使用resultMap
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	//查询订单（关联用户）及订单明细-使用resultMap
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
	
	//查询用户购买商品信息
	public List<User> findUserAndItemsResultMap() throws Exception;
	
	//查询订单关联查询用户，用户信息延迟加载
	public List<Orders> findOrdersUserLazyLoading() throws Exception;
	
	
}
