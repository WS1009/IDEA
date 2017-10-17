package cn.nuaa.service.impl;

import java.util.List;

import cn.nuaa.mapper.ItemsMapper;
import cn.nuaa.mapper.ItemsMapperCustom;
import cn.nuaa.po.Items;
import cn.nuaa.po.ItemsCustom;
import cn.nuaa.po.ItemsQueryVo;
import cn.nuaa.service.ItemsService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Title: ItemsServiceImpl</p>
 * <p>Description: 商品管理</p>
 * <p>Company: www.itcast.com</p>
 *
 * @version 1.0
 * @author 传智.燕青
 * @date 2015-4-13下午3:49:54
 */
@Service(value = "itemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;


    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.findItemsById(id);
        //中间进行业务处理
        //.....

        //返回一个扩展类
        ItemsCustom itemsCustom = null;
        //将items的属性值拷贝到itemsCustom
        if(items!=null){
            itemsCustom = new ItemsCustom();
            BeanUtils.copyProperties(items, itemsCustom);
        }

        return itemsCustom;
    }

    public void updateItems(int id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验，通常在service接口对关键参数进行校验
        //校验id是否为空，如果为空抛出异常

        //更新商品信息
        itemsCustom.setId(id);
        itemsMapper.updateItems(itemsCustom);
    }
}
