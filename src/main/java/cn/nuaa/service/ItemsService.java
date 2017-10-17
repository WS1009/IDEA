package cn.nuaa.service;

import java.util.List;

import cn.nuaa.po.Items;
import cn.nuaa.po.ItemsCustom;

import cn.nuaa.po.ItemsQueryVo;

/**
 * 
 * <p>Title: ItemsService</p>
 * <p>Description:商品管理service </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-4-13下午3:48:09
 * @version 1.0
 */
public interface ItemsService {
	
	//商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id)throws Exception;

	//修改商品信息
	public void updateItems(int id,ItemsCustom itemsCustom)throws Exception;
}
