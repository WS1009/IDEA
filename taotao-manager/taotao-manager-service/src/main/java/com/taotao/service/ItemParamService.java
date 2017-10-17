package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2017-08-21.
 */
public interface ItemParamService {
    EasyUIDataGridResult getItemParamList(int page, int rows);

    TaotaoResult getItemParamByCid(Long cid);

    TaotaoResult insertItemParam(Long cid, String paramData);
}
