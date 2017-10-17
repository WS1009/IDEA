package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2017-08-24.
 */
public interface ContentService {
    List<TbContent> getContentList(Long cid);
    public TaotaoResult syncContent(Long cid);
}
