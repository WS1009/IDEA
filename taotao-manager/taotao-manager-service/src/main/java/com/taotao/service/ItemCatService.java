package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by Administrator on 2017-08-16.
 */
public interface ItemCatService {
    public List<EasyUITreeNode> getItemCatList(long parentId);
}
