package cn.nuaa.mapper;


import cn.nuaa.po.Items;
import cn.nuaa.po.ItemsCustom;

public interface ItemsMapper {
    public Items findItemsById(int id) throws Exception;

    public void updateItems(ItemsCustom itemsCustom) throws Exception;

}