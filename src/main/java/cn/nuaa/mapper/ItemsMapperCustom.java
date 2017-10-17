package cn.nuaa.mapper;


import cn.nuaa.po.ItemsCustom;

import cn.nuaa.po.ItemsQueryVo;

import java.util.List;


public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}