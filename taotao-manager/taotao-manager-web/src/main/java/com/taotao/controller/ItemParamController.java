package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品规格参数模板管理
 * Created by Administrator on 2017-08-21.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(int page,int rows) {
        EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }


    @RequestMapping({"/query/itemcatid/{cid}","/cid/{cid}"})
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid) {
        TaotaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
        TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
        return result;
    }


}
