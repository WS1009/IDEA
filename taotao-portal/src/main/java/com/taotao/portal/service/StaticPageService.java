package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2017-08-30.
 */
public interface StaticPageService {
    public TaotaoResult genItemHtml(Long itemId) throws Exception;
}
