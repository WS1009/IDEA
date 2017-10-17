package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * Created by Administrator on 2017-08-30.
 */
public interface ItemService {
    public TbItem getItemById(Long itemId);
    public TbItemDesc getItemDescById(Long itemId);
    public TbItemParamItem getItemParamById(Long itemId);
}
