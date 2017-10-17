package cn.nuaa.po;

import java.util.List;

/**
 * 
 * @author WS
 * @date 创建时间：2017-6-22 下午4:28:51
 * @version 1.0
 */
public class UserQueryVo {
	// 在这里包装所需要的查询条件

	// 用户的查询条件
	private UserCustom userCustom;

	//传入多个ID
	private List<Integer> ids;


	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	// 可以包装其他的查询条件，订单、商品

}
